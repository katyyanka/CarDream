package org.bsuir.coursework.service;

import org.bsuir.coursework.entity.Place;
import org.bsuir.coursework.entity.Ticket;
import org.bsuir.coursework.entity.TicketId;
import org.bsuir.coursework.repository.PlaceRepository;
import org.bsuir.coursework.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket findById(TicketId id) {
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

    public void deleteById(TicketId id) {
        ticketRepository.deleteById(id);
    }



}
