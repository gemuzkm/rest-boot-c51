package com.example.restbootc51.converter;

import com.example.restbootc51.dto.OperationDTO;
import com.example.restbootc51.entity.Operation;

public class OperationDTOConverter {
    public Operation operationDTOtoOperation(OperationDTO operationDTO) {
        Operation operation = new Operation();
        operation.setValue1(operationDTO.getValue1());
        operation.setValue2(operationDTO.getValue2());
        operation.setOperation(operationDTO.getOperation());
        return operation;
    }
}
