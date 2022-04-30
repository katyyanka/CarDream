package org.bsuir.coursework.controller;

import org.bsuir.coursework.model.Order;
import org.bsuir.coursework.model.Ticket;
import org.bsuir.coursework.service.OrderService;
import org.bsuir.coursework.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TicketController {
    private final TicketService ticketService;
    private final OrderService orderService;

    @Autowired
    public TicketController(TicketService ticketService, OrderService orderService) {
        this.ticketService = ticketService;
        this.orderService = orderService;
    }


    @GetMapping("/tickets/{id}")
    public String findAll(@PathVariable("id") Integer id, Model model){
        List<Ticket> ticketList = ticketService.findAllTicketsForOrder(id);
        model.addAttribute("tickets", ticketList);
        model.addAttribute("id", id);
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        Integer positive = ticketService.findCountPositiveTicketsForOrder(id);
        Integer negative = ticketService.findCountNegativeTicketsForOrder(id);
        model.addAttribute("positive", positive);
        model.addAttribute("negative", negative);
        return "ticket/ticket-list";
    }

    @GetMapping("/user-tickets/{id}")
    public String findAll(@PathVariable("id") String id, Model model){
        List<Ticket> ticketList = ticketService.findAllActiveTicketsForClient(id);
        List<Ticket> archiveTicketList = ticketService.findAllArchiveTicketsForClient(id);
        model.addAttribute("tickets", ticketList);
        model.addAttribute("oldTickets", archiveTicketList);
        model.addAttribute("username", id);
        return "ticket/user-ticket-list";
    }

    @GetMapping("/ticket-create/{order}/{username}")
    public String findAll(@PathVariable("order") Integer order, @PathVariable("username") String username){

        ticketService.reserve(order, username);
        return "redirect: /../../../orders";
    }

    @GetMapping("ticket-delete/{id}")
    public String deletePlace(@PathVariable("id") int id){
        ticketService.deleteById(id);
        return "redirect:/orders";
    }

    @GetMapping("/ticket-update/{id}")
    public String ticketUpdate(@PathVariable("id") Integer id, Model model){
        Ticket ticket = ticketService.findById(id);
        model.addAttribute("ticket", ticket);
        List<Integer> freeSets = ticketService.getFreeSetsByTicketId(id);
        model.addAttribute("freeSets", freeSets);

        return "ticket/ticket-update";
    }

    @GetMapping("/ticket-status/{order}/{id}")
    public String ticketUpdateStatus(@PathVariable("id") Integer id,  @PathVariable("order") String order, Model model){
        ticketService.updateStatus(id);
        return "redirect:/tickets/"+order;
    }

    @PostMapping("/ticket-update")
    public String estimateTicketForm(Ticket ticket){
        ticketService.saveTicket(ticket);
        return "redirect:/orders";
    }

    @GetMapping("/ticket-estimate/{id}")
    public String ticketEstimate(@PathVariable("id") Integer id, Model model){
        Ticket ticket = ticketService.findById(id);
        model.addAttribute("ticket", ticket);
        return "ticket/ticket-estimate";
    }

    @PostMapping("/ticket-estimate")
    public String updatePlace(Ticket ticket){
        ticketService.updateComment(ticket.getComment(), ticket.getId());
        ticketService.updateMark(ticket.getMark(), ticket.getId());
        return "redirect:/orders";
    }

}
