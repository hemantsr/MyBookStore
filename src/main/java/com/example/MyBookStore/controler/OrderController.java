package com.example.MyBookStore.controler;

import com.example.MyBookStore.dao.OrderDao;
import com.example.MyBookStore.model.Order;
import com.example.MyBookStore.model.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderDao orderDao;

    @PutMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        String orderId = orderDao.placeOrder(orderRequest);
        return ResponseEntity.of(Optional.of(orderId));
    }

    @GetMapping("/get/order/{orderId}")
    public Order getOrder(@PathVariable String orderId) {
        return orderDao.getOrderByOrderid(orderId);
    }

    @GetMapping("/get/user/{userId}")
    public List<Order> getOrderByUserId(@PathVariable String userId) {
        return orderDao.getOrderByUserId(userId);
    }
}
