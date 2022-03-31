package com.example.restbootc51.controller;

import com.example.restbootc51.entity.User;
import com.example.restbootc51.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        Random random = new Random(user.hashCode());
        user.setToken("token" + random.nextInt() + user.getUsername());
        User save = userRepository.save(user);
        return ResponseEntity.ok(save);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> all = userRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/findByUsername")
    public ResponseEntity<User> findByUsername(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            return ResponseEntity.ok(byUsername.get());
        }
        return ResponseEntity.badRequest().build();
    }
}
