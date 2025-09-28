package app.api.Service;

import app.api.Exception.CustomException.UserAlreadyExistException;
import app.api.Persistence.DTOS.UserCreateDto;
import app.api.Persistence.DTOS.UserDto;
import app.api.Persistence.Entity.User;
import app.api.Persistence.Entity.VerificationToken;
import app.api.Persistence.Repo.UserRepo;
import app.api.Persistence.Repo.VerificationTokenRepo;
import app.api.Service.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final VerificationTokenRepo tokenRepo;

    public UserService(UserRepo userRepo, UserMapper userMapper, VerificationTokenRepo tokenRepo) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.tokenRepo = tokenRepo;
    }

    @Override
    public User persistUser(UserCreateDto userCreateDto)
    {

         if(EmailExist(userCreateDto.getEmail()))
         {
             throw new UserAlreadyExistException("There is an account with that email address:"
                     + userCreateDto.getEmail());
         }

         User user =userMapper.toEntity(userCreateDto);
         if(user == null || user.getEmail() == null) {
            throw new IllegalArgumentException("Invalid user data: mapping failed or email is missing");
        }

         user=userRepo.save(user);

         return user;

    }

   @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(token, user);
        tokenRepo.save(verificationToken);
    }
    @Override
    public UserDto verifyToken(String token) {
        VerificationToken verificationToken = tokenRepo.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid verification token"));

        if (verificationToken.getExpiryDate().before(new Date())) {
            throw new IllegalArgumentException("Verification token has expired");
        }

        User user = verificationToken.getUser();
        user.setEmailVerified(true);
        userRepo.save(user);

        return userMapper.toDto(user);
    }
    @Override
    public Cookie generateLogoutCookie()
    {
        Cookie cookie = new Cookie("token", "");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setAttribute("SameSite", "Strict");
        return cookie;
    }

    @Override
    public List<UserDto> getAllUsers()
    {
        List<User> users=userRepo.findAll();

        List<UserDto> userDtos=users.stream()
                                    .map(userMapper::toDto)
                                    .toList();
        return userDtos;
    }
    @Override
    public UserDto getUserById(Long id)
    {
        User user=findUserById(id);


        return userMapper.toDto(user);

    }
    @Override
    public UserDto updateUser(Long id, UserCreateDto userDto)
    {
        User user=findUserById(id);

        userMapper.updateExistingUser(user,userDto);
        if(user == null || user.getEmail() == null) {
            throw new IllegalArgumentException("Invalid user data: mapping failed or email is missing");
        }
        user=userRepo.save(user);
        return userMapper.toDto(user);


    }



    public User findUserById(Long id)
    {
        return userRepo.findById(id).orElseThrow(()->new EntityNotFoundException("User with id " + id+" not found"));
    }


    private boolean EmailExist(String email) {
        return userRepo.findByEmail(email)!=null;

    }
    private Optional<VerificationToken> getVerificationToken(String token) {
        return tokenRepo.findByToken(token);
    }

}



