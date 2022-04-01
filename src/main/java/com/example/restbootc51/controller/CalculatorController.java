package com.example.restbootc51.controller;

import com.example.restbootc51.entity.Operation;
import com.example.restbootc51.entity.User;
import com.example.restbootc51.enums.EnumOperation;
import com.example.restbootc51.repository.OperationRepository;
import com.example.restbootc51.repository.UserRepository;
import com.example.restbootc51.service.СalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("calc")
public class CalculatorController {
    public static final String HEADER_X_API_TOKEN = "X-API-Token";

    private final OperationRepository operationRepository;

    private final UserRepository userRepository;

    private final СalculatorService сalculatorService;

    public CalculatorController(OperationRepository operationRepository, UserRepository userRepository, СalculatorService сalculatorService) {
        this.operationRepository = operationRepository;
        this.userRepository = userRepository;
        this.сalculatorService = сalculatorService;
    }

    @PostMapping
    public ResponseEntity<Operation> calc(@RequestBody Operation operation, BindingResult bindingResult, @RequestHeader(HEADER_X_API_TOKEN) String token) {
        if (bindingResult.hasErrors() | token == null | userRepository.findByToken(token).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        if (Stream.of(EnumOperation.values()).anyMatch(t -> t.equals(operation.getOperation()))) {
            operation.setResult(сalculatorService.getResult(operation));
            operation.setUser(userRepository.findByToken(token).get());
            Operation save = operationRepository.save(operation);

            return ResponseEntity.ok(save);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/history")
    public ResponseEntity<List<Operation>> history(@RequestHeader(HEADER_X_API_TOKEN) String token) {
        if (token == null | userRepository.findByToken(token).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        User user = userRepository.findByToken(token).get();

        return ResponseEntity.ok(user.getOperationList());
    }
}
