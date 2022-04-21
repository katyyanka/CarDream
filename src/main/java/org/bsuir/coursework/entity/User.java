package org.bsuir.coursework.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@Entity
@ToString
@Table(name="user")
public class User {
    @Id
    private String username;
    private String password;
    private String lastname;
    private String name;
    private Date birthday;
    private String phone;
    private String role;
    private Boolean status;
    @OneToMany(mappedBy = "user")
    transient Set<Ticket> tickets;

}
