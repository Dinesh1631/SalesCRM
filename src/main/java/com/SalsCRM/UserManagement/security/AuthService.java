package com.SalsCRM.UserManagement.security;

import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SalsCRM.UserManagement.dto.AuthResponse;
import com.SalsCRM.UserManagement.dto.LoginRequest;
import com.SalsCRM.UserManagement.dto.RegisterRequest;
import com.SalsCRM.UserManagement.entity.User;
import com.SalsCRM.UserManagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public AuthService(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}
    public void register(RegisterRequest req) {
        if (userRepo.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        
        

        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRoles(Set.of("USER"));

        userRepo.save(user);
    }


	public AuthResponse login(LoginRequest req) {
        User user = userRepo.findByEmail(req.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(new org.springframework.security.core.userdetails.User(
            user.getEmail(), user.getPassword(),
            user.getRoles().stream().map(SimpleGrantedAuthority::new).toList()
        ));

        return new AuthResponse(token);
    }
}
