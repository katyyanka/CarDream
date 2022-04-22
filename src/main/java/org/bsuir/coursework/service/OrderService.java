package org.bsuir.coursework.service;

import org.bsuir.coursework.entity.Order;
import org.bsuir.coursework.entity.Place;
import org.bsuir.coursework.repository.OrderRepository;
import org.bsuir.coursework.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    public static final int ORDERS_PER_PAGE = 10;

    private final OrderRepository orderRepository;


    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order findById(Integer id) {
        return orderRepository.getOne(id);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order)  {
        return orderRepository.save(order);
    }
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }

    public List<Order> findAllOlderThanNow(){
        return orderRepository.findAllOlderThanNow();
    }

    public List<Double> distanceBetweenCities(){
        List<Double> distances = new ArrayList<>();
        for (Order order: findAllOlderThanNow()){
            distances.add(PlaceService.distanceBetweenCities(order.getDeparture(), order.getArrive()));
        }
        return distances;
    }

    public int countAllById(int id){
        return orderRepository.countAllById(id);
    }


    public List<Integer> countAllByIdArchive(){
        ArrayList<Integer> countOfTickets = new ArrayList<>();
        List<Order> orders = findAllOlderThanNow();
        for (Order order: orders) {
            countOfTickets.add(countAllById(order.getIdOrder()));
        }
        return countOfTickets;
    }

    public List<Integer> countAllByIdActive(){
        ArrayList<Integer> countOfTickets = new ArrayList<>();
        List<Order> orders = findAll();
        for (Order order: orders) {
            countOfTickets.add(countAllById(order.getIdOrder()));
        }
        return countOfTickets;
    }

    public int getOrderByTicketId(int ticketId){
        return orderRepository.getOrderByTicketId(ticketId);
    }
}
