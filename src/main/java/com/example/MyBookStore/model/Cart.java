package com.example.MyBookStore.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document(collection = "Cart")
public class Cart {

    @Id
    private String id;
    private Map<String, Integer> cartMembers;
}
