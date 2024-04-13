package hei.shool.transportationmanagementsystem.entities;

import hei.shool.transportationmanagementsystem.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transports")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Transport extends Model implements Serializable {

    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    private User driver;

    @ManyToOne
    private Route route;

    @ManyToOne
    private User customer;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "transport")
    private List<Expense> expenses = new ArrayList<>();
}
