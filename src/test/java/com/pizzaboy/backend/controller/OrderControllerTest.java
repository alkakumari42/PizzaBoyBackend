package com.pizzaboy.backend.controller;


import com.pizzaboy.backend.model.Order;
import com.pizzaboy.backend.model.Status;
import com.pizzaboy.backend.repository.OrderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    OrderRepository orderRepository;
    OrderController orderController;

    @BeforeEach
    public void setup() {
        orderRepository = mock(OrderRepository.class);
        orderController = new OrderController(orderRepository);
    }

    @Test
    public void createOrderTest_success() {
        Order order = createOrder();
        when(orderRepository.save(order)).thenReturn(order);
        String id = orderController.createOrder(order);
        Assertions.assertEquals(order.getId(), id);
        verify(orderRepository).save(order);
    }

    @Test
    public void createOrderTest_failure() {
        Order order = createOrder();
        doThrow(RuntimeException.class).when(orderRepository).save(order);
        Assertions.assertThrows(RuntimeException.class,
                () -> orderController.createOrder(order),
                "CreateOrder test failed to save");
        verify(orderRepository).save(order);
    }

    @Test
    public void getOrderTest_success() {
        String orderId = "234";
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(createOrder()));
        Order order = orderController.getOrder(orderId);
        Assertions.assertEquals(order, createOrder());
        verify(orderRepository).findById(orderId);
    }

    @Test
    public void getOrderTest_failure() {
        String orderId = "234";
        doThrow(RuntimeException.class).when(orderRepository).findById(orderId);
        Assertions.assertThrows(RuntimeException.class,
                () -> orderController.getOrder(orderId),
                "failed to find by id");
        verify(orderRepository).findById(orderId);
    }

    @Test
    public void getOrderTest_returnsNull() {
        String orderId = "234";
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());
        Assertions.assertEquals(new Order(), orderController.getOrder(orderId));
        verify(orderRepository).findById(orderId);
    }

    @Test
    public void getOrderByEmailTest_success() {
        String email = "test@yahoo.com";
        when(orderRepository.findByUserEmail(email)).thenReturn(createOrderList());
        Assert.assertEquals(createOrderList(), orderController.getOrderByEmail(email));
    }

    @Test
    public void getOrderByEmailTest_failure() {
        String email = "test@yahoo.com";
        doThrow(RuntimeException.class).when(orderRepository).findByUserEmail(email);
        Assertions.assertThrows(RuntimeException.class,
                () -> orderController.getOrderByEmail(email),
                "get order by email failed");
    }

    @Test
    public void getOrderByStatus_success() {
        String status = "rejected";
        when(orderRepository.findByStatus(status)).thenReturn(createOrderList());
        Assertions.assertEquals(createOrderList(), orderController.getOrderByStatus(status));
    }

    @Test
    public void getOrderByStatus_failure() {
        String status = "rejected";
        doThrow(RuntimeException.class).when(orderRepository).findByStatus(status);
        Assertions.assertThrows(RuntimeException.class,
                () -> orderController.getOrderByStatus(status),
                "get order by status failed");
    }

    @Test
    public void getAllOrderTest_success() {
        when(orderRepository.findAll()).thenReturn(createOrderList());
        Assertions.assertEquals(createOrderList(), orderController.getAllOrder());
        verify(orderRepository).findAll();
    }

    @Test
    public void getAllOrderTest_failure() {
        doThrow(RuntimeException.class).when(orderRepository).findAll();
        Assertions.assertThrows(RuntimeException.class,
                () -> orderController.getAllOrder(),
                "failed to get all order");
        verify(orderRepository).findAll();
    }

    @Test
    public void getAllOrderTest_returnsNull() {
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertEquals(new ArrayList<>(), orderController.getAllOrder());
        verify(orderRepository).findAll();
    }

    @Test
    public void updateOrderStatusTest_success() {
        String id = "1234";
        Status status = Status.DELIVERED;
        Order order = createOrder();
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);
        orderController.updateOrderStatus(id, status);
        verify(orderRepository).findById(id);
        verify(orderRepository).save(order);
    }

    @Test
    public void updateOrderStatusTest_findByIdFails() {
        String id = "1234";
        Status status = Status.DELIVERED;
        Order order = createOrder();
        doThrow(RuntimeException.class).when(orderRepository).findById(id);
        Assertions.assertThrows(RuntimeException.class,
                () -> orderController.updateOrderStatus(id, status),
                "Findby id fails");
        verify(orderRepository).findById(id);
        verify(orderRepository, never()).save(order);
    }

    @Test
    public void updateOrderStatusTest_saveFails() {
        String id = "1234";
        Status status = Status.DELIVERED;
        Order order = createOrder();
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));
        doThrow(RuntimeException.class).when(orderRepository).save(order);
        Assertions.assertThrows(RuntimeException.class,
                () -> orderController.updateOrderStatus(id, status),
                "save order fails");
        verify(orderRepository).findById(id);
        verify(orderRepository).save(order);
        Assertions.assertEquals("DELIVERED",order.getStatus());
    }

    private Order createOrder() {
        Order order = new Order();
        order.setOrderAmount(400);
        order.setOrderItems(Arrays.asList("bae41431", "cf19761b" ));
        order.setUserAddress("address-field");
        order.setUserEmail("test@gmail.com");
        return order;
    }
    private List<Order> createOrderList() {
        Order order = new Order();
        order.setOrderAmount(400);
        order.setOrderItems(Arrays.asList("bae41431", "cf19761b" ));
        order.setUserAddress("address-field");
        order.setUserEmail("test@gmail.com");

        Order order2 = new Order();
        order.setOrderAmount(700);
        order.setOrderItems(Arrays.asList("bae41431_2", "cf19761b_2" ));
        order.setUserAddress("address-field2");
        order.setUserEmail("test2@gmail.com");
        return Arrays.asList(order, order2);
    }

}
