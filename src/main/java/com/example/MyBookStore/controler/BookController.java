package com.example.MyBookStore.controler;

import com.example.MyBookStore.dao.BookDao;
import com.example.MyBookStore.model.Book;
import com.example.MyBookStore.utils.SerailizationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    private BookDao bookDao;

    @PostMapping("/save")
    public ResponseEntity<Book> saveBook(final @RequestBody Book book) {
        log.info("Add a new book to store:{}", SerailizationUtils.getGSON().toJson(book));
        Book savedBook = bookDao.Save(book);
        return ResponseEntity.of(Optional.of(savedBook));
    }

    @GetMapping("/get")
    public ResponseEntity<List<Book>> getAllBooks() {
        log.info("Getting all books from Book Store");
        List<Book> booksList = bookDao.getAllBooks();
        return ResponseEntity.of(Optional.of(booksList));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        log.info("Searching Book Store with BookId:{}", id);
        Book book = bookDao.getBookById(id);
        return ResponseEntity.of(Optional.of(book));
    }
}
