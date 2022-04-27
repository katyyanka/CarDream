package org.bsuir.coursework.repository;

import org.bsuir.coursework.entity.Order;
import org.bsuir.coursework.entity.Ticket;
import org.bsuir.coursework.entity.TicketId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query(value = "SELECT * FROM `ticket` where ORDER_ID = ?1",
            nativeQuery = true)
    List<Ticket> findAllTicketsForOrder(int orderId);

    @Query(value =
            "SELECT T.id, T.USER_EMAIL, T.ORDER_ID, T.SEET, T.COMMENT, T.MARK, T.STATUS " +
                    "FROM Ticket as T " +
                    "JOIN Orders AS O " +
                    "WHERE O.ID_ORDER = T.ORDER_ID AND O.DATETIME >= CURRENT_TIMESTAMP  AND  T.USER_EMAIL = ?1 ; ",
            nativeQuery = true)
    List<Ticket> findAllActiveTicketsForClient(String username);

    @Query(value =
            "SELECT COUNT(*) " +
                    "FROM Ticket as T " +
                    "JOIN Orders AS O " +
                    "WHERE O.ID_ORDER = T.ORDER_ID AND T.STATUS = 0 AND  T.ORDER_ID = ?1 ; ",
            nativeQuery = true)
    Integer findCountNegativeTicketsForOrder(Integer id);
    @Query(value =
            "SELECT COUNT(*) " +
                    "FROM Ticket as T " +
                    "JOIN Orders AS O " +
                    "WHERE O.ID_ORDER = T.ORDER_ID AND T.STATUS = 1 AND  T.ORDER_ID = ?1 ; ",
            nativeQuery = true)
    Integer findCountPositiveTicketsForOrder(Integer id);

    @Query(value =
            "SELECT T.id, T.USER_EMAIL, T.ORDER_ID, T.SEET, T.COMMENT, T.MARK, T.STATUS " +
                    "FROM Ticket as T " +
                    "JOIN Orders AS O " +
                    "ON T.ORDER_ID = O.ID_ORDER " +
                    "WHERE O.ID_ORDER = T.ORDER_ID AND O.DATETIME < CURRENT_TIMESTAMP AND T.USER_EMAIL = ?1 ",
            nativeQuery = true)
    List<Ticket> findAllArchiveTicketsForClient(String username);

    @Query(value = "Select vehicle.number_of_sets" +
            " FROM vehicle" +
            " join orders" +
            " on orders.driver_vehicle_number=vehicle.number" +
            " where orders.id_order = ?1",
            nativeQuery = true)
    int howManySetsInOrderCar(int orderId);

    @Query(value = "Select ticket.seet" +
            " FROM ticket" +
            " join orders" +
            " on orders.id_order=ticket.order_id" +
            " where orders.id_order = ?1",
            nativeQuery = true)
    List<Integer> busySets(int orderId);

    @Modifying
    @Query(value = "Insert into ticket(USER_EMAIL, ORDER_ID,`SEET`) " +
            "value(:username, :orderId, :set)",
            nativeQuery = true)
    @Transactional
    void reserve(@Param("username")String username, @Param("orderId") int orderId, @Param("set") int set);

    @Modifying
    @Query(value = "UPDATE ticket" +
            " set ticket.STATUS = 1" +
            " where ticket.id = ?1",
            nativeQuery = true)
    @Transactional
    void updateStatus(int ticket);

}
