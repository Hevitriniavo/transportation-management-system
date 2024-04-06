package hei.shool.transportationmanagementsystem.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public Map<String, String> sayHello(){
        return Map.of(
                "message",
                "Hello world"
        );
    }
}
