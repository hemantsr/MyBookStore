package com.example.MyBookStore.controler;

import com.example.MyBookStore.dao.BookDao;
import com.example.MyBookStore.dao.CartDao;
import com.example.MyBookStore.dao.UserDao;
import com.example.MyBookStore.model.Cart;
import com.example.MyBookStore.model.CartDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {

    @Autowired
    private CartDao cartDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;

    @GetMapping("/show/{id}")
    public ResponseEntity<Map<String, String>> showCart(@PathVariable String id) {
        Optional<Cart> cart = cartDao.getCartDetails(id);
        if (!cart.isPresent()) {
            throw new RuntimeException("No cart present");
        }

        Map<String, String> map = new HashMap<>();
        List<String> bookNames = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : cart.get().getCartMembers().entrySet()) {
            bookNames.add(bookDao.getBookById(entry.getKey()).getTitle());
        }

        map.put("UserName", userDao.getUserById(cart.get().getId()).getFirstName());
        map.put("Books", bookNames.toString());
        return ResponseEntity.of(Optional.of(map));
    }

    @PutMapping("/add")
    public ResponseEntity<Cart> addToCart(final @RequestBody CartDetails cartDetails) {

        log.info("Adding Books:{} to cart:{}", cartDetails.getBookids(), cartDetails.getUserId());
        return ResponseEntity.of(Optional.of(cartDao.addToCart(cartDetails.getUserId(), cartDetails.getBookids())));
    }
}
