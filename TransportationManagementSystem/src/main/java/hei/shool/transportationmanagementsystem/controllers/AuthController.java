package hei.shool.transportationmanagementsystem.controllers;

import hei.shool.transportationmanagementsystem.config.JwtService;
import hei.shool.transportationmanagementsystem.dtos.requests.CredentialRequest;
import hei.shool.transportationmanagementsystem.entities.User;
import hei.shool.transportationmanagementsystem.entities.UserType;
import hei.shool.transportationmanagementsystem.services.UserService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;


    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;


    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody @Valid CredentialRequest user){
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.email(),
                    user.password()
            ));
            var loggedInUser = userService.findByEmail(user.email());
           var joinedUserTypes = loggedInUser.getUserTypes().stream()
                .map(UserType::getType) // Get the type string from each UserType object
                .collect(Collectors.joining(" "));
           var token = jwtService.generateToken(loggedInUser, Map.of(
                    "roles",
                joinedUserTypes
            ));
            loggedInUser.setToken(token);
            userService.register(loggedInUser);
            return Map.of(
                    "accessToken",
                    token
            );
    }
}
