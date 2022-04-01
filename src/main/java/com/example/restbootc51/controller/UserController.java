package com.example.restbootc51.controller;

import com.example.restbootc51.entity.User;
import com.example.restbootc51.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        user.setToken("token" + new Random().nextInt(user.hashCode()) + LocalDateTime.now().getSecond() + user.getUsername());
        User save = userRepository.save(user);
        return ResponseEntity.ok(save);
    }

//    @GetMapping
//    public ResponseEntity<List<User>> findAll() {
//        List<User> all = userRepository.findAll();
//        return ResponseEntity.ok(all);
//    }
//
//    @GetMapping("/findByUsername")
//    public ResponseEntity<User> findByUsername(String username) {
//        Optional<User> byUsername = userRepository.findByUsername(username);
//        if (byUsername.isPresent()) {
//            return ResponseEntity.ok(byUsername.get());
//        }
//        return ResponseEntity.badRequest().build();
//    }
}
