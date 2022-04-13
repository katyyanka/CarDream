package org.bsuir.coursework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="vehicle")
public class Vehicle {
    @Id
    @Column(name="number")
    private String number;
    @Column(name="NUMBER_OF_SETS")
    private int numberOfSets;
    private String brand;
    @Column(name="FUEL_CONSUMPTION_PER_100_KILOMETERS")
    private int fuelConsumptionPer100Kilometers;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "DRIVER_EMAIL", referencedColumnName = "USERNAME")
    private User user;
    @ManyToOne
    @JoinColumn(name="FUEL_NAME")
    private Fuel fuel;
}
