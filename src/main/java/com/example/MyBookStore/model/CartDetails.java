package com.example.MyBookStore.model;

import lombok.Data;

import java.util.List;

@Data
public class CartDetails {
    private String userId;
    private List<String> bookids;
}
