package hei.shool.transportationmanagementsystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cities")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class City extends Model implements Serializable {
    private String name;

    @OneToMany(mappedBy = "originCity")
    private List<Route> originRoutes = new ArrayList<>();

    @OneToMany(mappedBy = "destinationCity")
    private List<Route> destinationRoutes = new ArrayList<>();
}
