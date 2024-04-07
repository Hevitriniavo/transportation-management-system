package hei.shool.transportationmanagementsystem.services.impl;

import hei.shool.transportationmanagementsystem.entities.User;
import hei.shool.transportationmanagementsystem.enums.Gender;
import hei.shool.transportationmanagementsystem.repositories.UserRepository;
import hei.shool.transportationmanagementsystem.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserServiceImplIntegrationTest {
    @Autowired
    private UserService userService;

    private User user;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @AfterEach
    void tearDown() {
        if (user != null) {
            userRepository.delete(user);
        }
    }

    @Test
    void testRegister() {
        user = User.builder()
                .email("hello@example.com")
                .password("1234!")
                .firstName("A TEST")
                .lastName("A TEST AGAIN")
                .phoneNumber("123456789")
                .gender(Gender.M)
                .build();
        userService.register(user);

        final Optional<User> userCreated = userRepository.findByEmail("hello@example.com");

        assertThat(userCreated).isPresent();
        assertThat(passwordEncoder.matches("1234!", userCreated.get().getPassword())).isTrue();
    }
}