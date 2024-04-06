package hei.shool.transportationmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @CreatedDate
    @Column(updatable = false)
    protected LocalDate createdAt;

    @LastModifiedDate
    protected LocalDate updatedAt;
}
