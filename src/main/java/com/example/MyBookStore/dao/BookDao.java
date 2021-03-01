package com.example.MyBookStore.dao;

import com.example.MyBookStore.model.Book;
import com.example.MyBookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component

public class BookDao {

    @Autowired
    private BookRepository bookRepository;

    public Book Save(final Book book) {

        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(books::add);
        return books;
    }

    public Book getBookById(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            return bookOptional.get();
        }

        throw new RuntimeException("No Book find for give Id");
    }
}
