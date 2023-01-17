package com.pizzaboy.backend.controller;

import com.pizzaboy.backend.model.Order;
import com.pizzaboy.backend.model.Status;
import com.pizzaboy.backend.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private final OrderRepository orderRepository;

    @PostMapping("/create")
    public String createOrder(@RequestBody Order order) {
        order.setStatus(Status.WAITING_FOR_CONFIRMATION.getName());
        //TODO validate order
        return orderRepository.save(order).getId();
    }

    @GetMapping("/id")
    public Order getOrder(@RequestParam String orderId) {
        System.out.println("OrderId = "+orderId);
       return orderRepository.findById(orderId).orElse(new Order());
    }

    @GetMapping("/email")
    public List<Order> getOrderByEmail(@RequestParam String email) {
        System.out.println("email = "+email);
        return orderRepository.findByUserEmail(email);
    }

    @GetMapping("/status")
    public List<Order> getOrderByStatus(@RequestParam String status) {
        System.out.println("status = "+status);
        return orderRepository.findByStatus(status);
    }

    @GetMapping
    public List<Order> getAllOrder() {
        System.out.println("getAllOrder");
        List<Order> list = new ArrayList<>();
        orderRepository.findAll().forEach(list::add);
        return list;
    }

    @PostMapping("/updateStatus")
    public void updateOrderStatus(String id, Status status) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order doesn't exist"));
        order.setStatus(status.getName());
        orderRepository.save(order);
    }
}
