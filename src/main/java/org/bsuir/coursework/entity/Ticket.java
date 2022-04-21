package org.bsuir.coursework.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ticket")
public class Ticket {
    @Id
    @Column(name= "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name="user_email")
    User user;

    @ManyToOne
    @JoinColumn(name="order_id")
    Order order;

    String comment;

    int mark;

    int set;

    boolean status;

}
