package com.example.MyBookStore.dao;

import com.example.MyBookStore.model.Cart;
import com.example.MyBookStore.model.Order;
import com.example.MyBookStore.model.OrderRequest;
import com.example.MyBookStore.repository.CartRepository;
import com.example.MyBookStore.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderDao {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;

    public String placeOrder(OrderRequest orderRequest) {

        //read from cart and build order details.
        Cart cart = cartRepository.findById(orderRequest.getUserId()).get();
        Order order = new Order();
        order.setOrderElements(cart.getCartMembers());
        order.setOrderId(orderRequest.getUserId());
        order.setPaymentDetails(orderRequest.getPaymentDetails());
        //save order details.
        String orderId = orderRepository.save(order).getOrderId();
        log.info("OrderId is:{}", orderId);
        //delete from cart.
        cartRepository.deleteById(orderRequest.getUserId());
        return orderId;
    }
}
