package app.api.Controller;

import app.api.Persistence.DTOS.Responses.SuccessResponse;
import app.api.Persistence.DTOS.UserCreateDto;
import app.api.Persistence.DTOS.UserDto;
import app.api.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class UserController
{
    private final UserService userService;
    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;

    }

    @PostMapping("/create-user")
    public ResponseEntity<SuccessResponse<UserDto>> createUser(@RequestBody @Valid UserCreateDto request)
    {
       UserDto userDto=userService.persistUser(request);

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(new SuccessResponse<>("User is created successfully",userDto));

    }





}
