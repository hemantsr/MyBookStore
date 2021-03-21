package com.example.MyBookStore.dao;

import com.example.MyBookStore.constants.PublisherConstants;
import com.example.MyBookStore.kafka.KafkaPublisher;
import com.example.MyBookStore.model.Cart;
import com.example.MyBookStore.model.Order;
import com.example.MyBookStore.model.OrderRequest;
import com.example.MyBookStore.repository.CartRepository;
import com.example.MyBookStore.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class OrderDao {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private KafkaPublisher kafkaPublisher;

    public String placeOrder(OrderRequest orderRequest) {

        //read from cart and build order details.
        Cart cart = cartRepository.findById(orderRequest.getUserId()).get();
        Order order = new Order();
        order.setOrderElements(cart.getCartMembers());
        order.setOrderId(orderRequest.getUserId());
        order.setPaymentDetails(orderRequest.getPaymentDetails());
        //save order details.
        String orderId = orderRepository.save(order).getOrderId();
        kafkaPublisher.sendMessage(order, PublisherConstants.ORDER_CREATION_TOPIC);
        log.info("OrderId is:{}", orderId);
        //delete from cart.
        cartRepository.deleteById(orderRequest.getUserId());

        return orderId;
    }

    public Order getOrderByOrderid(String orderId) {
        Optional<Order> orderFetched = orderRepository.findById(orderId);
        return orderFetched.orElseThrow(() -> new RuntimeException("No Matching Order Present"));
    }

    public List<Order> getOrderByUserId(String userId) {
        return orderRepository.findOrderByUserId(userId)
            .orElseThrow(() -> new RuntimeException("No Matching Order Present"));
    }
}
