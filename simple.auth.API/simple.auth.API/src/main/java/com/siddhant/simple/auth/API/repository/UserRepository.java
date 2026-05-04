package com.siddhant.simple.auth.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.siddhant.simple.auth.API.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
