package com.SalsCRM.UserManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String refreshToken;

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public AuthResponse(String token,String refreshToken) {
		
		this.token = token;
		this.refreshToken = refreshToken;
	}
public AuthResponse(String token) {
		
		this.token = token;
		
	}
}