package org.bsuir.coursework.model;

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

    @Column(name = "seet")
    int set;

    boolean status;

    public static Builder newBuilder() {
        return new Ticket().new Builder();
    }

    public class Builder {

        private Builder() { }

        public Builder setId(int id) {
            Ticket.this.id = id;
            return this;
        }

        public Builder setUser(User user) {
            Ticket.this.user = user;
            return this;
        }

        public Builder setOrder(Order order) {
            Ticket.this.order = order;
            return this;
        }

        public Builder setComment(String comment) {
            Ticket.this.comment = comment;
            return this;
        }
        public Builder setMark(int mark) {
            Ticket.this.mark = mark;
            return this;
        }

        public Builder setSet(int set) {
            Ticket.this.set = set;
            return this;
        }

        public Builder setStatus(Boolean status) {
            Ticket.this.status = status;
            return this;
        }

        public Ticket build() {
            return Ticket.this;
        }

    }
}
