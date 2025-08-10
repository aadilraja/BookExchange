package app.api.Service;

import app.api.Exception.CustomException.UserAlreadyExistException;
import app.api.Persistence.DTOS.UserCreateDto;
import app.api.Persistence.DTOS.UserDto;
import app.api.Persistence.Entity.User;
import app.api.Persistence.Repo.UserRepo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public UserDto persistUser(UserCreateDto request)
    {

         if(EmailExist(request.getEmail()))
         {
             throw new UserAlreadyExistException("There is an account with that email address:"
                     + request.getEmail());
         }





    }

    private boolean EmailExist(String email) {
        return userRepo.findByEmail(email)!=null;

    }
}
