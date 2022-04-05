package org.bsuir.coursework.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name="user")
public class User {
    @Id
    private String username;
    private String password;
    @Column(name = "Is_Admin")
    private Boolean isAdmin;
    private String lastname;
    private String name;
    private Date birthday;
    private String phone;
    private String role;
}
