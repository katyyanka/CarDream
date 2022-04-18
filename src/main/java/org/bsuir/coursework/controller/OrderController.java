package org.bsuir.coursework.controller;

import org.bsuir.coursework.entity.Order;
import org.bsuir.coursework.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/orders")
    public String findAll(Model model){
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        List<Order> old_orders = orderService.findAllOlderThanNow();
        model.addAttribute("old_orders", old_orders);
        List<Double> distances = orderService.distanceBetweenCities();
        model.addAttribute("distances", distances);
        return "order/order-list";
        //return findPaginated(1, model);
    }

    @GetMapping("/orders/{pageNumber}")
    public String findPaginated(@PathVariable( value = "pageNumber") int pageNumber, Model model){
      /*  Page<Order> page = orderService.findPaginated(pageNumber, OrderService.ORDERS_PER_PAGE);
        List<Order> orders = page.getContent();

        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("orders", orders);*/
        return "order/order-list";
    }
}
