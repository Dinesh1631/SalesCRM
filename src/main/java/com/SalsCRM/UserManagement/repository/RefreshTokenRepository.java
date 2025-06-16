package com.SalsCRM.UserManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SalsCRM.UserManagement.entity.RefreshToken;
import com.SalsCRM.UserManagement.entity.User;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    int deleteByUser(User user);
}
