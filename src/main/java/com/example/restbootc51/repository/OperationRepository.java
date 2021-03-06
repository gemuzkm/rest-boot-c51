package com.example.restbootc51.repository;

import com.example.restbootc51.entity.Operation;
import com.example.restbootc51.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
