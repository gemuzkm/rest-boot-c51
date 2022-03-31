package com.example.restbootc51.controller;

import com.example.restbootc51.entity.Operation;
import com.example.restbootc51.repository.OperationRepository;
import com.example.restbootc51.repository.UserRepository;
import com.example.restbootc51.service.СalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("calc")
public class CalculatorController {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private СalculatorService сalculatorService;

    @PostMapping
    public ResponseEntity<Operation> calc(@RequestBody Operation operation, @RequestHeader("X-API-Token") String token) {
        double result = сalculatorService.getResult(operation);
        operation.setResult(result);
        Operation save = operationRepository.save(operation);
        return ResponseEntity.ok(save);
    }
}
