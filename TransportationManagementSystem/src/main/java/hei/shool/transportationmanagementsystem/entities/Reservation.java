package hei.shool.transportationmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Reservation  extends Model implements Serializable  {

    @ManyToOne
    private User user;


    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Place> places = new ArrayList<>();

    private LocalDateTime reservationDate;

    private LocalDateTime departureDate;

    @OneToMany(mappedBy = "reservation")
    private List<Payment> payments = new ArrayList<>();

}
