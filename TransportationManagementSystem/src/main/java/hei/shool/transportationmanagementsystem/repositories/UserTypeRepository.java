package hei.shool.transportationmanagementsystem.repositories;

import hei.shool.transportationmanagementsystem.entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {
    Optional<UserType> findByType(String type);
}
