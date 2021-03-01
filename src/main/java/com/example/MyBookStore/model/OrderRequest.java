package com.example.MyBookStore.model;

import lombok.Data;

@Data
public class OrderRequest {
    private String userId;
    private PaymentDetails paymentDetails;
}
