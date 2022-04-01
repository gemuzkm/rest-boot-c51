package com.example.restbootc51.controller;

import com.example.restbootc51.entity.Operation;
import com.example.restbootc51.entity.User;
import com.example.restbootc51.repository.OperationRepository;
import com.example.restbootc51.repository.UserRepository;
import com.example.restbootc51.service.СalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("calc")
public class CalculatorController {

    private final OperationRepository operationRepository;

    private final UserRepository userRepository;

    private final СalculatorService сalculatorService;

    public CalculatorController(OperationRepository operationRepository, UserRepository userRepository, СalculatorService сalculatorService) {
        this.operationRepository = operationRepository;
        this.userRepository = userRepository;
        this.сalculatorService = сalculatorService;
    }

    @PostMapping
    public ResponseEntity<Operation> calc(@RequestBody Operation operation, BindingResult bindingResult, @RequestHeader("X-API-Token") String token) {
        if (bindingResult.hasErrors() | token == null | userRepository.findByToken(token).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        operation.setResult(сalculatorService.getResult(operation));
        operation.setUser(userRepository.findByToken(token).get());
        Operation save = operationRepository.save(operation);

        return ResponseEntity.ok(save);
    }

    @GetMapping("/history")
    public ResponseEntity<List<Operation>> history(@RequestHeader("X-API-Token") String token) {
        if (token == null | userRepository.findByToken(token).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        User user = userRepository.findByToken(token).get();
        return ResponseEntity.ok(user.getOperationList());
    }
}
