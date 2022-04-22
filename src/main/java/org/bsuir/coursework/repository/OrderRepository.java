package org.bsuir.coursework.repository;

import org.bsuir.coursework.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "SELECT * FROM `ORDERS` where DATETIME>CURRENT_TIMESTAMP ",
            nativeQuery = true)
    List<Order> findAll();

    @Query(value = "SELECT * FROM `ORDERS` where DATETIME<=CURRENT_TIMESTAMP ",
            nativeQuery = true)
    List<Order> findAllOlderThanNow();

    @Query(value = "SELECT COUNT(*) FROM ticket JOIN Orders ON TICKET.ORDER_ID=ORDERS.ID_ORDER WHERE Orders.id_ORDER=?1",
            nativeQuery = true)
    int countAllById(int id);

}
