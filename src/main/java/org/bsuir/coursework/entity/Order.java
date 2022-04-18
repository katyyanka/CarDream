package org.bsuir.coursework.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table (name = "order")
public class Order implements Serializable {
    @Id
    @Column(name= "id_ORDER")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer idOrder;
    float price;

    @ManyToOne
    @JoinColumn(name="driver_email")
    User user;
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
    @Column(name="occupied_places")
    Integer occupiedPlaces;
}
