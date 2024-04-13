package hei.shool.transportationmanagementsystem.repositories;

import hei.shool.transportationmanagementsystem.entities.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        Role toCreate = Role.builder()
                .name("USER")
                .build();
        roleRepository.save(toCreate);
    }

    @Test
    void testFindByName() {
        Optional<Role> foundRoleOptional = roleRepository.findByName("USER");
        Role expected = Role.builder().name("USER").build();
        assertThat(foundRoleOptional).isPresent();
        assertThat(foundRoleOptional.get().getId()).isNotNull();
        assertThat(foundRoleOptional.get()).usingRecursiveComparison().ignoringFields("id", "createdAt", "updatedAt").isEqualTo(expected);
    }
}