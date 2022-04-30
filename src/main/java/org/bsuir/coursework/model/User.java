package org.bsuir.coursework.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@Entity
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
    @OneToMany(mappedBy = "user")
    transient Set<Ticket> tickets;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public User(String username, String password, String lastname, String name, Date birthday, String phone, String role, Set<Ticket> tickets) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.role = role;
        this.tickets = tickets;
    }

    public User(String username, String password, String lastname, String name, Date birthday, String phone, String role) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.role = role;
    }
}
