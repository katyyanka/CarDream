package org.bsuir.coursework.service;

import org.bsuir.coursework.entity.Order;
import org.bsuir.coursework.entity.Ticket;
import org.bsuir.coursework.entity.TicketId;
import org.bsuir.coursework.entity.User;
import org.bsuir.coursework.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    final TicketRepository ticketRepository;
    final UserService userService;
    final OrderService orderService;

    @Autowired
    public TicketService(TicketRepository ticketRepository, UserService userService, OrderService orderService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
        this.orderService = orderService;
    }

    public Ticket findById(Integer id) {
        return ticketRepository.getOne(id);
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public List<Ticket> findAllTicketsForOrder(int orderId) {
        return ticketRepository.findAllTicketsForOrder(orderId);
    }

    public List<Ticket> findAllActiveTicketsForClient(String username) {
        return ticketRepository.findAllActiveTicketsForClient(username);
    }

    public List<Ticket> findAllArchiveTicketsForClient(String username) {
        return ticketRepository.findAllArchiveTicketsForClient(username);
    }


    public Ticket savePlace(Ticket ticket) throws Exception {
        return ticketRepository.save(ticket);
    }

    public void deleteById(int id) {
        ticketRepository.deleteById(id);
    }

    private List<Integer> busySets(int id) {
        return ticketRepository.busySets(id);
    }
    /**     BUILDER  HERE      */
    public void reserve(int order, String username) {
        int numberOfCarSets = ticketRepository.howManySetsInOrderCar(order);
        List<Integer> busySets = busySets(order);
        if (busySets.size() >= numberOfCarSets) {
            return;
        }
        List<Integer> allSets = new ArrayList<>();
        for (int i = 0; i < numberOfCarSets; i++) {
            allSets.add(i + 1);
        }
        allSets.removeAll(busySets);
        List<Integer> freeSets = allSets;
        System.out.println(freeSets);
        User user = userService.findById(username);
        Order orderEntity = orderService.findById(order);
        Ticket ticket = new Ticket();
        ticket.newBuilder()
                .setUser(user)
                .setOrder(orderEntity)
                .setSet(freeSets.get(0))
                .build();
        //ticketRepository.save(ticket);
        ticketRepository.reserve(username,order,freeSets.get(0));
    }

}
