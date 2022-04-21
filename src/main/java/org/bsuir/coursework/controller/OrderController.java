package org.bsuir.coursework.controller;

import org.bsuir.coursework.entity.Order;
import org.bsuir.coursework.entity.Place;
import org.bsuir.coursework.service.OrderService;
import org.bsuir.coursework.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final PlaceService placeService;

    @Autowired
    public OrderController(OrderService orderService, PlaceService placeService) {
        this.orderService = orderService;
        this.placeService = placeService;
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
        return "order/order-list";
        //return findPaginated(1, model);
    }
}
