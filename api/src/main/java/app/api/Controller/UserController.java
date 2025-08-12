package app.api.Controller;

import app.api.Persistence.DTOS.AuthRequest;
import app.api.Persistence.DTOS.Responses.SuccessResponse;
import app.api.Persistence.DTOS.UserCreateDto;
import app.api.Persistence.DTOS.UserDto;
import app.api.Service.JwtService;
import app.api.Security.MyUserDetailsService;
import app.api.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class UserController
{
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    @Autowired
    public UserController(UserService userService, JwtService jwtService,
                          AuthenticationManager authenticationManager,
                          MyUserDetailsService userDetailsService)
    {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;

    }

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse<UserDto>> createUser(@RequestBody @Valid UserCreateDto request)
    {
       UserDto userDto=userService.persistUser(request);

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(new SuccessResponse<>("User is created successfully",userDto));

    }
    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<String>>authUser(@RequestBody @Valid AuthRequest authRequest)
    {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );
        UserDetails user = userDetailsService.loadUserByUsername(authRequest.getEmail());

        final String jwtToken = jwtService.generateToken(user);

        return ResponseEntity.ok(new SuccessResponse<>("User logged in successfully",jwtToken));

    }
    @GetMapping("/userView")
    public String userView()
    {
        return "hello loged user";
    }





}
