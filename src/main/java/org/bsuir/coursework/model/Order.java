package org.bsuir.coursework.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table (name = "orders")
public class Order {
    @Id
    @Column(name= "id_ORDER")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer idOrder;
    float price;

    @ManyToOne
    @JoinColumn(name="driver_vehicle_number")
    Vehicle vehicle;
    Timestamp datetime;
    @ManyToOne
    @JoinColumn(name="arrive_place")
    Place arrive;
    @ManyToOne
    @JoinColumn(name="departure_place")
    Place departure;
    @OneToMany(mappedBy = "order")
    transient Set<Ticket> tickets;
}
