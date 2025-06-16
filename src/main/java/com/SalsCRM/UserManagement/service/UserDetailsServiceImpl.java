package com.SalsCRM.UserManagement.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SalsCRM.UserManagement.entity.User;
import com.SalsCRM.UserManagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepo;

    public UserDetailsServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
            user.getEmail(), user.getPassword(),
            user.getRoles().stream().map(SimpleGrantedAuthority::new).toList()
        );
    }
}

