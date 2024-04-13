package hei.shool.transportationmanagementsystem.repositories;

import hei.shool.transportationmanagementsystem.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTypeRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
