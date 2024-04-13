package hei.shool.transportationmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "places")
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Place extends Model implements Serializable {

   private Integer placeNumber;

   private String description;

   @ManyToOne
   private Vehicle vehicle;

   @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
   private List<User> users = new ArrayList<>();

   @ManyToOne
   private Reservation reservation;
}
