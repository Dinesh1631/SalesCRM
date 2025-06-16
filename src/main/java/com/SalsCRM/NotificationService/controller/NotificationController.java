package com.SalsCRM.NotificationService.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SalsCRM.NotificationService.dto.EmailRequest;
import com.SalsCRM.NotificationService.service.EmailService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final EmailService emailService;

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        emailService.sendLeadAssignmentEmail(
                request.getTo(),
                request.getLeadName(),
                request.getAssignedBy(),
                request.getAssigneeName()
        );
        return ResponseEntity.ok("Email sent to " + request.getTo());
    }
}

