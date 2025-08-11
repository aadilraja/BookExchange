package app.api.Service;

import app.api.Exception.CustomException.UserAlreadyExistException;
import app.api.Persistence.DTOS.UserCreateDto;
import app.api.Persistence.DTOS.UserDto;
import app.api.Persistence.Entity.User;
import app.api.Persistence.Repo.UserRepo;
import app.api.Service.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserService(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto persistUser(UserCreateDto request)
    {

         if(EmailExist(request.getEmail()))
         {
             throw new UserAlreadyExistException("There is an account with that email address:"
                     + request.getEmail());
         }

         User user =userMapper.toEntity(request);
         if(user == null || user.getEmail() == null) {
            throw new IllegalArgumentException("Invalid user data: mapping failed or email is missing");
        }
         user=userRepo.save(user);

         return userMapper.toDto(user);

    }

    private boolean EmailExist(String email) {
        return userRepo.findByEmail(email)!=null;

    }
}
