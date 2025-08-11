package app.api.Service;

import app.api.Persistence.DTOS.UserCreateDto;
import app.api.Persistence.DTOS.UserDto;

public interface IUserService {
    public UserDto persistUser(UserCreateDto request);

}
