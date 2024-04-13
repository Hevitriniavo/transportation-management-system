package hei.shool.transportationmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(callSuper = true)
public class Vehicle extends Model implements Serializable {

    @Column(unique = true)
    private String registration;

    private String model;

    private Integer capacity;

    private Double speed;

    private Boolean isAvailable = true;

    private String photo;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Place> places = new ArrayList<>();

    @OneToMany(mappedBy = "vehicle")
    private List<Transport> transports = new ArrayList<>();
}
