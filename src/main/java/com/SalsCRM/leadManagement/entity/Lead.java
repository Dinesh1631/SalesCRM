package com.SalsCRM.leadManagement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "leads")
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String status;
    private String assignedTo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Lead() {}

    public Lead(String name, String email, String phone, String status, String assignedTo) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.assignedTo = assignedTo;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }public Lead(String name, String email, String phone, String status) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
       
    }
}