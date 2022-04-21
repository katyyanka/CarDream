package org.bsuir.coursework.repository;

import org.bsuir.coursework.entity.Order;
import org.bsuir.coursework.entity.Ticket;
import org.bsuir.coursework.entity.TicketId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, TicketId> {

    @Query(value = "SELECT * FROM `ticket` where ORDER_ID = ?1",
            nativeQuery = true)
    List<Ticket> findAllTicketsForOrder(int orderId);

    @Query(value =
            "SELECT T.id, T.USER_EMAIL, T.ORDER_ID, T.SET, T.COMMENT, T.MARK, T.STATUS " +
                    "FROM Ticket as T " +
                    "JOIN Orders AS O " +
                    "WHERE O.ID_ORDER = T.ORDER_ID AND O.DATETIME >= CURRENT_TIMESTAMP  AND  T.USER_EMAIL = ?1 ; ",
            nativeQuery = true)
    List<Ticket> findAllActiveTicketsForClient(String username);

    @Query(value =
            "SELECT T.id, T.USER_EMAIL, T.ORDER_ID, T.SET, T.COMMENT, T.MARK, T.STATUS " +
                    "FROM Ticket as T " +
                    "JOIN Orders AS O " +
                    "ON T.ORDER_ID = O.ID_ORDER " +
                    "WHERE O.ID_ORDER = T.ORDER_ID AND O.DATETIME < CURRENT_TIMESTAMP AND T.USER_EMAIL = ?1 ",
            nativeQuery = true)
    List<Ticket> findAllArchiveTicketsForClient(String username);


}