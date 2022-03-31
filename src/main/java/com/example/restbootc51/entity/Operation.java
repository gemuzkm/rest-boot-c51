package com.example.restbootc51.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="HISTORY")
@AllArgsConstructor
@NoArgsConstructor
public class Operation {

    private static final String MSG_INVALID_VALUE1 = "invalid value1";
    private static final String MSG_INVALID_VALUE2 = "invalid value2";
    private static final String MSG_NO_OPERATOR_IS_SUPPORT = "No operator is supported";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = MSG_INVALID_VALUE1)
    private Double value1;

    @NotNull(message = MSG_INVALID_VALUE2)
    private Double value2;

    @NotNull(message = MSG_NO_OPERATOR_IS_SUPPORT)
    private String operation;

    private Double result;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;


    public Double getValue1() {
        return value1;
    }

    public void setValue1(Double value1) {
        this.value1 = value1;
    }

    public Double getValue2() {
        return value2;
    }

    public void setValue2(Double value2) {
        this.value2 = value2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
