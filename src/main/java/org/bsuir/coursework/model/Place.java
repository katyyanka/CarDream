package org.bsuir.coursework.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="place")
public class Place {
    @Id
    String name;
    String longitude;
    String width;
}
