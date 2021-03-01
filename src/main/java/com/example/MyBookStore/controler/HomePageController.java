package com.example.MyBookStore.controler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/home")
public class HomePageController {

    @GetMapping()
    public ResponseEntity<String> welcome() {
        return ResponseEntity.of(Optional.of("Welcome to My Book Store"));
    }
}
