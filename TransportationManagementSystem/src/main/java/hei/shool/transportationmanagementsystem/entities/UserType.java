package hei.shool.transportationmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_types")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class UserType extends Model {

    private String type;

    @ManyToMany(mappedBy = "userTypes", cascade = CascadeType.ALL)
    List<User> users = new ArrayList<>();
}
