package com.example.MyBookStore.model;

import com.example.MyBookStore.utils.SerailizationUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Books")
public class Book {

    @Id
    private String id;
    private String title;
    private String author;
    private Genre genre;

    @Override
    public String toString() {
        return SerailizationUtils.getGSON().toJson(this);
    }
}
