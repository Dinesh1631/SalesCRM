package com.SalsCRM.UserManagement.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SalsCRM.UserManagement.dto.AuthResponse;
import com.SalsCRM.UserManagement.dto.LoginRequest;
import com.SalsCRM.UserManagement.dto.RefreshTokenRequest;
import com.SalsCRM.UserManagement.dto.RegisterRequest;
import com.SalsCRM.UserManagement.entity.RefreshToken;
import com.SalsCRM.UserManagement.exception.ResourceNotFoundException;
import com.SalsCRM.UserManagement.repository.RefreshTokenRepository;
import com.SalsCRM.UserManagement.security.AuthService;
import com.SalsCRM.UserManagement.security.JwtUtil;
//import com.SalsCRM.UserManagement.service.RefreshTokenService;
import com.SalsCRM.UserManagement.service.UserDetailsServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
   // private final RefreshTokenService refreshTokenService;
    private final UserDetailsServiceImpl userDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, 
			UserDetailsServiceImpl userDetailsService,RefreshTokenRepository refreshTokenRepository,JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
		this.authService = authService;
//		this.refreshTokenService = refreshTokenService;
		this.userDetailsService = userDetailsService;
		this.refreshTokenRepository= refreshTokenRepository;
		this.jwtUtil  = jwtUtil;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

	@PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
//    @PostMapping("/api/auth/refresh")
//    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
//        return refreshTokenRepository.findByToken(request.getRefreshToken())
//                .map(refreshTokenService::verifyExpiration)
//                .map(RefreshToken::getUser)
//                .map(user -> {
//                    String token = jwtUtil.generateToken(
//                            new org.springframework.security.core.userdetails.User(
//                                    user.getEmail(),
//                                    user.getPassword(),
//                                    user.getRoles().stream().map(SimpleGrantedAuthority::new).toList()
//                            ));
//                    return ResponseEntity.ok(new AuthResponse(token, request.getRefreshToken()));
//                })
//                .orElseThrow(() -> new ResourceNotFoundException("Refresh token not found"));
//    }
}

