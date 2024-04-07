package hei.shool.transportationmanagementsystem.controllers;

import hei.shool.transportationmanagementsystem.entities.User;
import hei.shool.transportationmanagementsystem.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> sayHello(){
        return  userRepository.findAll();
    }
}
