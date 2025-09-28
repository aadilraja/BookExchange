package app.api.Controller;

import app.api.Persistence.DTOS.Responses.SuccessResponse;
import app.api.Persistence.DTOS.UserCreateDto;
import app.api.Persistence.DTOS.UserDto;
import app.api.Persistence.Entity.User;
import app.api.Service.IUserService;
import app.api.Service.UserService;
import app.api.Service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController extends BaseController {

    private final IUserService userService;

    @Autowired
    public UserController(MessageSource msg, UserService userService) {
        super(msg);
        this.userService = userService;
    }

    //Admin
    @GetMapping
    public ResponseEntity<SuccessResponse<List<UserDto>>> getAllUsers() {
        List<UserDto> usersDto = userService.getAllUsers();

        String responseMsg=createResponseMsg("message.user.retrieved");

        return ResponseEntity.ok().body(new SuccessResponse<>(responseMsg, usersDto));

    }

    //User
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<UserDto>> getUserById(@PathVariable("id") Long id) {
        UserDto userDto=userService.getUserById(id);
        String responseMsg=createResponseMsg("message.user.retrieved.id");
        return ResponseEntity.ok().body(new SuccessResponse<>(responseMsg, userDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<UserDto>> updateUser(@PathVariable("id") Long id,
                                                      @RequestBody UserCreateDto dto)
    {
        UserDto userDto=userService.updateUser(id,dto);

         String responseMsg=createResponseMsg("message.user.updated");
         return ResponseEntity.ok().body(new SuccessResponse<>(responseMsg, userDto));
    }

}
