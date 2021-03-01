package com.example.MyBookStore.dao;

import com.example.MyBookStore.model.Cart;
import com.example.MyBookStore.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class CartDao {

    @Autowired
    private CartRepository cartRepository;

    public Cart addToCart(final String userId,
                          final List<String> bookIds) {


        if (CollectionUtils.isEmpty(bookIds)) {
            throw new RuntimeException("List can not be empty");
        }

        Optional<Cart> existingCart = cartRepository.findById(userId);
        Cart cart;
        //create new cart and add books to it.
        if (!existingCart.isPresent()) {
            log.info("Cart does not exist for userId:{}", userId);
            cart = new Cart();
            cart.setCartMembers(getBookToQunatityMap(bookIds, new HashMap<>()));
            cart.setId(userId);
        } else {
            log.info("Updating the existing cart for User:{}", userId);
            //get existing cart and add new books to it.
            Map<String, Integer> existingCartMember = existingCart.get().getCartMembers();
            existingCart.get().setCartMembers(getBookToQunatityMap(bookIds, existingCartMember));
            cart = existingCart.get();
        }
        cartRepository.save(cart);
        return cart;
    }

    public Optional<Cart> getCartDetails(final String id) {
        return cartRepository.findById(id);
    }

    private Map<String, Integer> getBookToQunatityMap(List<String> bookIds,
                                                    Map<String, Integer> bookQuantityMap) {

        for (String bookId : bookIds) {
            if (bookQuantityMap.containsKey(bookId)) {
                bookQuantityMap.put(bookId, bookQuantityMap.get(bookId) + 1);
            } else {
                bookQuantityMap.put(bookId, 1);
            }
        }
        return bookQuantityMap;
    }
}
