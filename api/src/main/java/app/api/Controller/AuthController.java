package app.api.Controller;

import app.api.Persistence.DTOS.AuthRequest;
import app.api.Persistence.DTOS.Responses.SuccessResponse;
import app.api.Persistence.DTOS.UserCreateDto;
import app.api.Persistence.DTOS.UserDto;
import app.api.Persistence.Entity.User;
import app.api.Registeration.OnRegistrationCompleteEvent;
import app.api.Service.IUserService;
import app.api.Service.JwtService;
import app.api.Security.MyUserDetailsService;
import app.api.Service.mapper.UserMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController
{
    private final IUserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final MessageSource msg;
    @Autowired
    public AuthController(IUserService userService, JwtService jwtService,
                          AuthenticationManager authenticationManager,
                          UserMapper userMapper,
                          ApplicationEventPublisher eventPublisher,
                          MessageSource msg)
    {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.eventPublisher = eventPublisher;
        this.msg = msg;

    }

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse<UserDto>> createUser(@RequestBody @Valid UserCreateDto userCreateDto,
                                                               HttpServletRequest request)
    {
               User user=userService.persistUser(userCreateDto);


               eventPublisher.publishEvent(new OnRegistrationCompleteEvent(
                       user,
                       request.getLocale()
               ));
               return ResponseEntity
                       .status(HttpStatus.CREATED)
                       .body(new SuccessResponse<>("User is created successfully",userMapper.toDto(user)));

    }
    @GetMapping("/registration-confirm")
    public ResponseEntity<SuccessResponse<?>> confirmRegistration(@RequestParam("token") String token)
    {

        UserDto userDto=userService.verifyToken(token);
        String message = msg.getMessage("success.user.verified",
                new Object[]{userDto.getEmail()},
                LocaleContextHolder.getLocale()
        );

        return ResponseEntity.ok(new SuccessResponse<>(message));

    }
    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<?>>authenticateUser(@RequestBody @Valid AuthRequest authRequest,
                                                              HttpServletResponse response,
                                                              HttpServletRequest request)
    {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        final Cookie token = jwtService.generateToken(authRequest,request);


        response.addCookie(token);

        return ResponseEntity.ok(new SuccessResponse<>("User logged in successfully"));

    }
    @PostMapping("/logout")
    public ResponseEntity<SuccessResponse<?>>logoutUser(HttpServletResponse response)
    {
        Cookie cookie = userService.generateLogoutCookie();
        response.addCookie(cookie);

        return ResponseEntity.ok(new SuccessResponse<>("User logged out successfully"));

    }




}
