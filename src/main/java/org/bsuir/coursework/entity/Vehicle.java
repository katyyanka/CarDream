package org.bsuir.coursework.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@ToString
@Table(name="vehicle")
public class Vehicle {
    @Id
    private String number;
    @Column(name="NUMBER_OF_SETS")
    private int numberOfSets;
    private String brand;
    @Column(name="FUEL_CONSUMPTION_PER_100_KILOMETERS")
    private int fuelConsumptionPer100Kilometers;
    /*@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "DRIVER_EMAIL", referencedColumnName = "USERNAME")*/
    @OneToOne
    @MapsId
    @JoinColumn(name = "driver_email")
    private User user;
    @Column(name="FUEL_NAME")
    private String fuel;
}
