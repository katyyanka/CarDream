package org.bsuir.coursework.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ticket")
@IdClass(TicketId.class)
public class Ticket {
    @Id
    @ManyToOne
    @JoinColumn(name="user_email")
    User user;
    @Id
    @ManyToOne
    @JoinColumn(name="order_id")
    Order order;

    String comment;

    int mark;

    int set;

    boolean status;

}
