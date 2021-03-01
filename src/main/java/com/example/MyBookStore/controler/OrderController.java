package com.example.MyBookStore.controler;

import com.example.MyBookStore.dao.OrderDao;
import com.example.MyBookStore.model.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
