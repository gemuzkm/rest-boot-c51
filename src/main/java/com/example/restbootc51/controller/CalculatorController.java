package com.example.restbootc51.controller;

import com.example.restbootc51.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("calc")
public class CalculatorController {

    @Autowired
    private OperationRepository operationRepository;


}
