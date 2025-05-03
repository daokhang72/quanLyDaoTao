package com.QLDaoTao.repository;

import com.QLDaoTao.model.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUserEmail(String userEmail);
    Optional<User> findByUserEmailAndUserPassword(String email, String password);
}
