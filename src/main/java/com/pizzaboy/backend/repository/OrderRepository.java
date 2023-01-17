package com.pizzaboy.backend.repository;

import com.pizzaboy.backend.model.Order;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableScan
@Repository
public interface OrderRepository extends CrudRepository<Order, String> {
    public List<Order> findByStatus(String status);
    public List<Order> findByUserEmail(String userEmail);
    Optional<Order> findById(String id);

}
