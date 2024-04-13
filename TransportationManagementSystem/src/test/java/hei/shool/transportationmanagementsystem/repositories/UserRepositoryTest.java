package hei.shool.transportationmanagementsystem.repositories;

import hei.shool.transportationmanagementsystem.entities.User;
import hei.shool.transportationmanagementsystem.enums.Gender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .lastName("Doe")
                .firstName("John")
                .email("john.doe@example.com")
                .phoneNumber("0344544467")
                .gender(Gender.M)
                .password("password")
                .build();
        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        userRepository.delete(user);
    }

    @Test
    void testFindByEmail() {
        Optional<User> foundUserOptional = userRepository.findByEmail(user.getEmail());
        assertThat(foundUserOptional).isPresent();
        assertThat(foundUserOptional.get())
                .usingRecursiveComparison()
                .ignoringFields("id", "createdAt", "updatedAt")
                .isEqualTo(user);
    }


    @Test
    void testNotFindByEmail() {
        Optional<User> foundUserOptional = userRepository.findByEmail("nonexistent@example.com");
        assertThat(foundUserOptional).isEmpty();
    }
}
