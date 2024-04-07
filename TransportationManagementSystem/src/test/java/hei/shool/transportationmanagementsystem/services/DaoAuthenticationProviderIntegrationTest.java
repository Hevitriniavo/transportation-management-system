package hei.shool.transportationmanagementsystem.services;

import hei.shool.transportationmanagementsystem.entities.User;
import hei.shool.transportationmanagementsystem.enums.Gender;
import hei.shool.transportationmanagementsystem.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class DaoAuthenticationProviderIntegrationTest {
    @Autowired
    private AuthenticationProvider customDaoAuthenticator;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("hello@example.com")
                .password("1234!")
                .firstName("A TEST")
                .lastName("A TEST AGAIN")
                .phoneNumber("123456789")
                .gender(Gender.M)
                .build();
        userService.register(user);
    }

    @AfterEach
    void tearDown() {
        if (user != null) {
            userRepository.delete(user);
        }
    }

    @Test
    void testAuthenticate() {
        var result = customDaoAuthenticator.authenticate(new UsernamePasswordAuthenticationToken("hello@example.com", "1234!"));

        assertThat(result.isAuthenticated()).isTrue();
    }
}