package com.example.MyBookStore.repository;

import com.example.MyBookStore.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {

    Optional<List<Order>> findOrderByUserId(String userId);
}
