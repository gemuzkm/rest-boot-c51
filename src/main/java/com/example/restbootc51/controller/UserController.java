package com.example.restbootc51.controller;

import com.example.restbootc51.entity.User;
import com.example.restbootc51.repository.UserRepository;
import com.example.restbootc51.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {
    public static final String HEADER_X_API_TOKEN = "X-API-Token";

    private final UserValidator userValidator;

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    @PostMapping
    public ResponseEntity<User> save(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        User save = userRepository.save(user);
        return ResponseEntity.ok(save);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        if (userValidator.isValid(user)) {
            User userFromBD = userRepository.findByUsername(user.getUsername()).get();
            userFromBD.setToken("token" + new Random().nextInt(user.hashCode()) + LocalDateTime.now().getSecond() + user.getUsername());
            userRepository.save(userFromBD);

            return ResponseEntity.ok(userFromBD);
        }

        return ResponseEntity.badRequest().build();
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
