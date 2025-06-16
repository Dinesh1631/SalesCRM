package com.SalsCRM.UserManagement.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class RegisterRequest {
    @SuppressWarnings("deprecation")
	@NotBlank
    private String name;

    @SuppressWarnings("deprecation")
	@Email @NotBlank
    private String email;

    @SuppressWarnings("deprecation")
	@NotBlank
    private String password;

	public RegisterRequest(@NotBlank String name, @Email @NotBlank String email, @NotBlank String password) {
		
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
