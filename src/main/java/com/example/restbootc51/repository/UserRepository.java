package com.example.restbootc51.repository;

import com.example.restbootc51.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
