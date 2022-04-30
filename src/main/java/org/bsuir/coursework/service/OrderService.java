package org.bsuir.coursework.service;

import org.bsuir.coursework.model.Order;
import org.bsuir.coursework.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public double financeResult(Integer id){
        Order order = orderRepository.getOne(id);
        Double distance = PlaceService.distanceBetweenCities(order.getDeparture(), order.getArrive());
        Double financeResult = 0.0;
        Double expenses = distance/100*order.getVehicle().getFuel().getPrice()*order.getVehicle().getFuelConsumptionPer100Kilometers();
        Double income = (double) (orderRepository.countAllById(id) * order.getPrice());
        financeResult = income - expenses;
        return financeResult;
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

    public List<Order> findAllByDriver(String driver) {
        return orderRepository.findAllByDriver(driver);
    }
    public List<Order> findAllOldByDriver(String driver) {
        return orderRepository.findAllOldByDriver(driver);
    }

}
