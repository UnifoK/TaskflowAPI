package io.github.unifok.taskflowapi.controller;

import io.github.unifok.taskflowapi.dto.LoginRequest;
import io.github.unifok.taskflowapi.dto.LoginResponse;
import io.github.unifok.taskflowapi.model.User;
import io.github.unifok.taskflowapi.service.JwtService;
import io.github.unifok.taskflowapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequest loginRequest) {
        // 1. Authenticate the user with Spring Security's manager
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // 2. If authentication is successful, find the user
        var user = userService.loadUserByUsername(loginRequest.getUsername());

        // 3. Generate a JWT for that user
        var jwtToken = jwtService.generateToken(user);

        // 4. Return the token in a response object
        return new LoginResponse(jwtToken);
    }
    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
