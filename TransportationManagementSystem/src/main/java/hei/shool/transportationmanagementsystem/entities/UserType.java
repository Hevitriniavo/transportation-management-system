package hei.shool.transportationmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.*;

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
}
