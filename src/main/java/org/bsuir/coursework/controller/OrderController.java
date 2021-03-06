package org.bsuir.coursework.controller;

import com.itextpdf.text.DocumentException;
import org.bsuir.coursework.model.Order;
import org.bsuir.coursework.model.Place;
import org.bsuir.coursework.model.Ticket;
import org.bsuir.coursework.model.Vehicle;
import org.bsuir.coursework.helpers.OrderDocument;
import org.bsuir.coursework.service.OrderService;
import org.bsuir.coursework.service.PlaceService;
import org.bsuir.coursework.service.TicketService;
import org.bsuir.coursework.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final PlaceService placeService;
    private final VehicleService vehicleService;
    private final TicketService ticketService;

    @Autowired
    public OrderController(OrderService orderService, PlaceService placeService, VehicleService vehicleService, TicketService ticketService) {
        this.orderService = orderService;
        this.placeService = placeService;
        this.vehicleService = vehicleService;
        this.ticketService = ticketService;
    }


    @GetMapping("/orders")
    public String findAll(Model model){
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        List<Order> old_orders = orderService.findAllOlderThanNow();
        model.addAttribute("old_orders", old_orders);
        List<Double> distances = orderService.distanceBetweenCities();
        model.addAttribute("distances", distances);
        List<Place> places = placeService.findAll();
        model.addAttribute("places", places);
        List<Integer> archiveTicketCount = orderService.countAllByIdArchive();
        List<Integer> activeTicketCount = orderService.countAllByIdActive();
        model.addAttribute("archive", archiveTicketCount);
        model.addAttribute("active", activeTicketCount);
        return "order/order-list";
    }

    @GetMapping("/driver-orders/{id}")
    public String findAllForDriver(@PathVariable("id") String id, Model model){
        List<Order> orders = orderService.findAllByDriver(id);
        model.addAttribute("orders", orders);
        List<Order> old_orders = orderService.findAllOldByDriver(id);
        model.addAttribute("old_orders", old_orders);
        List<Place> places = placeService.findAll();
        model.addAttribute("places", places);
        return "order/driver-order-list";
    }

    @GetMapping("/order-create")
    public String createOrderForm(Order order, String datetimeLocal, Model model){
        List<Place> places = placeService.findAll();
        model.addAttribute("places", places);
        List<Vehicle> vehicles = vehicleService.findAll();
        model.addAttribute("vehicles", vehicles);
        return "order/order-create";
    }

    @PostMapping("/order-create")
    public String createOrder(@RequestParam("datetimeLocal") String datetimeLocal, Order order){
        if (StringUtils.countMatches(datetimeLocal, ":") == 1) {
            datetimeLocal += ":00";
        }
        order.setDatetime(Timestamp.valueOf(datetimeLocal.replace("T", " ")));
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("order-delete/{id}")
    public String deleteOrder(@PathVariable("id") int id){
        orderService.deleteById(id);
        return "redirect:/orders";
    }

    @GetMapping("/order-update/{id}")
    public String updateOrderForm(@PathVariable("id") Integer id, Model model){
        List<Place> places = placeService.findAll();
        model.addAttribute("places", places);
        List<Vehicle> vehicles = vehicleService.findAll();
        model.addAttribute("vehicles", vehicles);
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return "order/order-update";
    }

    @PostMapping("/order-update")
    public String updateOrder(@RequestParam("datetimeLocal") String datetimeLocal,Order order){
        if (StringUtils.countMatches(datetimeLocal, ":") == 1) {
            datetimeLocal += ":00";
        }
        order.setDatetime(Timestamp.valueOf(datetimeLocal.replace("T", " ")));
        orderService.updateOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/order/{id}/pdf")
    public void exportToPDF(@PathVariable("id") Integer id, HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=orderInfo.pdf";
        response.setHeader(headerKey, headerValue);

        List<Ticket> tickets = ticketService.findAllTicketsForOrder(id);
        Order order = orderService.findById(id);
        OrderDocument exporter = new OrderDocument(
                tickets,
                order
        );
        exporter.export(response);
    }
}
