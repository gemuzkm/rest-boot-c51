package com.example.restbootc51.repository;

import com.example.restbootc51.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
