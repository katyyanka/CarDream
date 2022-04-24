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

    @Query(value = "SELECT ORDER_ID FROM ticket WHERE ID = ?1 ",
            nativeQuery = true)
    int getOrderByTicketId(int ticketId);

    @Query(value = "SELECT O.* FROM ORDERS AS O\n" +
            "JOIN vehicle AS V\n" +
            "ON O.DRIVER_VEHICLE_NUMBER = V.NUMBER\n" +
            "JOIN USER AS U\n" +
            "ON U.USERNAME = V.DRIVER_EMAIL\n" +
            "WHERE U.USERNAME = ?1\n" +
            "and O.DATETIME >= CURRENT_TIMESTAMP",
            nativeQuery = true)
    List<Order> findAllByDriver(String driver);

    @Query(value = "SELECT O.* FROM ORDERS AS O\n" +
            "JOIN vehicle AS V\n" +
            "ON O.DRIVER_VEHICLE_NUMBER = V.NUMBER\n" +
            "JOIN USER AS U\n" +
            "ON U.USERNAME = V.DRIVER_EMAIL\n" +
            "WHERE U.USERNAME = ?1\n" +
            "and O.DATETIME < CURRENT_TIMESTAMP",
            nativeQuery = true)
    List<Order> findAllOldByDriver(String driver);

}
