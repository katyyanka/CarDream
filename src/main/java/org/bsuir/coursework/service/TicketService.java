package org.bsuir.coursework.service;

import org.bsuir.coursework.model.Order;
import org.bsuir.coursework.model.Ticket;
import org.bsuir.coursework.model.User;
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

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void updateStatus(int id) {
        ticketRepository.updateStatus(id);
    }

    public void deleteById(int id) {
        ticketRepository.deleteById(id);
    }

    private List<Integer> busySets(int id) {
        return ticketRepository.busySets(id);
    }

    /**
     * BUILDER  HERE
     */
    public void reserve(int order, String username) {
        List<Integer> freeSets = getFreeSets(order);
        User user = userService.findById(username);
        Order orderEntity = orderService.findById(order);
        ticketRepository.reserve(username, order, freeSets.get(0));
    }

    public List<Integer> getFreeSets(int order) {
        int numberOfCarSets = ticketRepository.howManySetsInOrderCar(order);
        List<Integer> busySets = busySets(order);
        if (busySets.size() >= numberOfCarSets) {
            return null;
        }
        List<Integer> allSets = new ArrayList<>();
        for (int i = 0; i < numberOfCarSets; i++) {
            allSets.add(i + 1);
        }
        allSets.removeAll(busySets);
        List<Integer> freeSets = allSets;
        return freeSets;
    }

    public List<Integer> getFreeSetsByTicketId(int id) {
        int order = orderService.getOrderByTicketId(id);
        return getFreeSets(order);
    }

    public Integer findCountNegativeTicketsForOrder(int id) {
        return ticketRepository.findCountNegativeTicketsForOrder(id);
    }

    public Integer findCountPositiveTicketsForOrder(int id) {
        return ticketRepository.findCountPositiveTicketsForOrder(id);
    }

    public void updateComment(String comment, int id) {
        ticketRepository.updateComment(comment, id);
    }

    public void updateMark(int mark, int id) {
        ticketRepository.updateMark(mark, id);
    }
}
