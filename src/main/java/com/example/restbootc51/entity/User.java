package com.example.restbootc51.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {
    private static final String MSG_NAME_EMPTY = "name empty";
    private static final String MSG_NAME_3_TO_50_CHARACTERS = "name should be between 3 and 50 characters";
    private static final String MSG_PASSWORD_EMPTY = "password empty";
    private static final String MSG_PASSWORD_3_TO_50_CHARACTERS = "password should be between 3 and 50 characters";
    private static final String MSG_EMAIL_EMPTY = "email empty";
    private static final String MSG_EMAIL_NOT_VALID = "not valid email";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = MSG_NAME_EMPTY)
    @Size(min = 3, max = 50, message = MSG_NAME_3_TO_50_CHARACTERS)
    private String username;

    @NotNull(message = MSG_PASSWORD_EMPTY)
    @Size(min = 3, max = 50, message = MSG_PASSWORD_3_TO_50_CHARACTERS)
    private String password;

    private String token;

//    @NotNull(message = MSG_EMAIL_EMPTY)
//    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = MSG_EMAIL_NOT_VALID)
//    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Operation> operationList;
}
