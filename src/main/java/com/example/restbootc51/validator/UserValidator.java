package com.example.restbootc51.validator;

import com.example.restbootc51.entity.User;
import com.example.restbootc51.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    @Autowired

    private UserRepository userRepository;

    public boolean isValid(User user) {
        return isValidUsername(user.getUsername()) & isValidUserPassword(user);
    }

    private boolean isValidUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    private boolean isValidUserPassword(User user) {
        User userFromBD = userRepository.findByUsername(user.getUsername()).get();
        return userFromBD.getPassword().equals(user.getPassword());
    }
}
