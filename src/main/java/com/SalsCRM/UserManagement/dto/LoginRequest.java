package com.SalsCRM.UserManagement.dto;

import org.hibernate.validator.constraints.Email;

import lombok.Data;

@Data
public class LoginRequest {
    @SuppressWarnings("deprecation")
	@Email
    private String email;
    private String password;
	public LoginRequest(@Email String email, String password) {
		
		this.email = email;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
