//package com.SalsCRM.UserManagement.service;
//
//
//
//import java.time.Instant;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.SalsCRM.UserManagement.entity.RefreshToken;
//import com.SalsCRM.UserManagement.entity.User;
//import com.SalsCRM.UserManagement.exception.ResourceNotFoundException;
//import com.SalsCRM.UserManagement.repository.RefreshTokenRepository;
//import com.SalsCRM.UserManagement.repository.UserRepository;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class RefreshTokenService {
//
//    private final RefreshTokenRepository refreshTokenRepository;
//    private final UserRepository userRepository;
//    public RefreshTokenService(
//            RefreshTokenRepository refreshTokenRepository, UserRepository userRepository,
//            @Value("${jwt.refresh-expiration-ms}") long refreshExpirationMs) {
//            this.refreshTokenRepository = refreshTokenRepository;
//            this.refreshExpirationMs = refreshExpirationMs;
//            this.userRepository = userRepository;
//        }
//   
//
//	@Value("${jwt.refresh.expiration}")
//    private Long refreshExpirationMs;
//
//    public RefreshToken createRefreshToken(String email) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//
//        RefreshToken refreshToken = new RefreshToken();
//        refreshToken.setUser(user);
//        refreshToken.setToken(UUID.randomUUID().toString());
//        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshExpirationMs));
//        return refreshTokenRepository.save(refreshToken);
//    }
//
//    public RefreshToken verifyExpiration(RefreshToken token) {
//        if (token.getExpiryDate().isBefore(Instant.now())) {
//            refreshTokenRepository.delete(token);
//            throw new IllegalArgumentException("Refresh token has expired. Please login again.");
//        }
//        return token;
//    }
//
//    @Transactional
//    public int deleteByUserId(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//        return refreshTokenRepository.deleteByUser(user);
//    }
//}
//
