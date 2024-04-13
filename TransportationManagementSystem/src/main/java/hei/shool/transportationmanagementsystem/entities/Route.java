package hei.shool.transportationmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "routes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(callSuper = true)
public class Route extends Model implements Serializable {

    @ManyToOne
    private City originCity;

    @ManyToOne
    private City destinationCity;

    private Double distanceKm;

    private Integer estimatedDurationMinutes;

    @OneToMany(mappedBy = "route")
    private List<Transport> transports = new ArrayList<>();
}
