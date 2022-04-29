package org.bsuir.coursework.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="fuel")
public class Fuel {
    @Id
    String name;
    Float price;
}
