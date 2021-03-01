package com.example.MyBookStore.controler;

import com.example.MyBookStore.dao.UserDao;
import com.example.MyBookStore.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private final UserDao userDao;

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(final @RequestBody User user) {
        log.info("Creating user:{}", user);
        User savedUser = userDao.save(user);
        return ResponseEntity.of(Optional.of(savedUser));
    }

    @GetMapping("/get")
    public List<User> getAllUser() {
        return userDao.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable String id) {
        return userDao.getUserById(id);
    }

}
