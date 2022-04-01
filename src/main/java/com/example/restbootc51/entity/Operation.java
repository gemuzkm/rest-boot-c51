package com.example.restbootc51.entity;

import com.example.restbootc51.enums.EnumOperation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="HISTORY")
@Data
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
//    private String operation;
    private EnumOperation operation;

    private Double result;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        List<Operation> operationList = user.getOperationList();
        operationList.add(this);
        this.user = user;
    }
}
