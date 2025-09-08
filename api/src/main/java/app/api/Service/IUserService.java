package app.api.Service;

import app.api.Persistence.DTOS.UserCreateDto;
import app.api.Persistence.DTOS.UserDto;
import app.api.Persistence.Entity.User;
import app.api.Persistence.Entity.VerificationToken;
import jakarta.servlet.http.Cookie;

public interface IUserService {
    User persistUser(UserCreateDto request);
   void createVerificationToken(User user, String token);
//    VerificationToken getVerificationToken(String token);
//    void enableUser(User user);

    UserDto verifyToken(String token);

    Cookie generateLogoutCookie();
}
