package com.example.MyBookStore.repository;

import com.example.MyBookStore.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
