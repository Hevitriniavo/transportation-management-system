package hei.shool.transportationmanagementsystem.controllers;

import hei.shool.transportationmanagementsystem.config.JwtService;
import hei.shool.transportationmanagementsystem.dtos.requests.CredentialRequest;
import hei.shool.transportationmanagementsystem.entities.User;
import hei.shool.transportationmanagementsystem.entities.UserType;
import hei.shool.transportationmanagementsystem.enums.Gender;
import hei.shool.transportationmanagementsystem.repositories.UserRepository;
import hei.shool.transportationmanagementsystem.services.FileService;
import hei.shool.transportationmanagementsystem.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final FileService fileService;

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;


    @PostMapping("/register")
    public Map<String, String> register(
        @RequestParam(value = "email", required = false) String email,
        @RequestParam("lastName") String lastName,
        @RequestParam("firstName") String firstName,
        @RequestParam("gender") String gender,
        @RequestParam("password") String password,
        @RequestParam("phoneNumber") String phoneNumber,
        @RequestParam("photo") MultipartFile photo
    ) {

        String url = fileService.saveFile(photo, "users");
        User user = User.builder()
                .email(email)
                .lastName(lastName)
                .firstName(firstName)
                .gender(Gender.valueOf(gender))
                .password(password)
                .phoneNumber(phoneNumber)
                .photo(url)
                .build();
        return userService.register(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody @Valid CredentialRequest user){
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        user.email(),
                        user.password()
                ));

        var loggedInUser = userService.findByEmail(user.email());
           var joinedUserTypes = loggedInUser.getUserTypes().stream()
                .map(UserType::getType)
                .collect(Collectors.joining(" "));
           var token = jwtService.generateToken(loggedInUser, Map.of(
                    "roles",
                joinedUserTypes,
                   "image", loggedInUser.getPhoto()
            ));
            userRepository.save(loggedInUser);
            return Map.of(
                    "accessToken",
                    token
            );
    }
}
