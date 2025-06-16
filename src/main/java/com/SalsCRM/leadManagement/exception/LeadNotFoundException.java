package com.SalsCRM.leadManagement.exception;

public class LeadNotFoundException extends RuntimeException {
    public LeadNotFoundException(String message) {
        super(message);
    }
}