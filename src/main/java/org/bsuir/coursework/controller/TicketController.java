package org.bsuir.coursework.controller;

import org.bsuir.coursework.entity.Place;
import org.bsuir.coursework.entity.Ticket;
import org.bsuir.coursework.service.PlaceService;
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

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @GetMapping("/tickets/{id}")
    public String findAll(@PathVariable("id") Integer id, Model model){
        List<Ticket> ticketList = ticketService.findAllTicketsForOrder(id);
        model.addAttribute("tickets", ticketList);
        model.addAttribute("id", id);
        return "ticket/ticket-list.html";
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

   /* @GetMapping("/place-create")
    public String createPlaceForm(Place place){
        return "place/place-create.html";
    }

    @PostMapping("/place-create")
    public String createPlace(Place place){
        try {
            placeService.savePlace(place);
            return "redirect:/places";
        } catch (Exception e) {
            return "redirect:/400";
        }
    }

    @GetMapping("place-delete/{id}")
    public String deletePlace(@PathVariable("id") String id){
        placeService.deleteById(id);
        return "redirect:/places";
    }

    @GetMapping("/place-update/{id}")
    public String updatePlaceForm(@PathVariable("id") String id, Model model){
        Place place = placeService.findById(id);
        model.addAttribute("place", place);
        return "place/place-update.html";
    }

    @PostMapping("/place-update")
    public String updatePlace(Place place){
        placeService.updatePlace(place);
        return "redirect:/places";
    }*/

}
