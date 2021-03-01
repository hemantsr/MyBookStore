package com.example.MyBookStore.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document(collection = "Order")
public class Order {

    @Id
    private String orderId;
    private String userId;
    private Map<String, Integer> orderElements;
    private PaymentDetails paymentDetails;
}
