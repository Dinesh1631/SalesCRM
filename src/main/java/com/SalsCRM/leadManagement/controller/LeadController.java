package com.SalsCRM.leadManagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SalsCRM.leadManagement.dto.LeadRequest;
import com.SalsCRM.leadManagement.dto.LeadResponse;
import com.SalsCRM.service.LeadService;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @PostMapping("/create")
    public ResponseEntity<LeadResponse> createLead(@RequestBody LeadRequest request) {
        return ResponseEntity.ok(leadService.createLead(request));
    }

    @GetMapping("getAllLeads")
    public ResponseEntity<List<LeadResponse>> getAllLeads() {
        return ResponseEntity.ok(leadService.getAllLeads());
    }

    @PutMapping("/{id}/assign")
    public ResponseEntity<LeadResponse> assignLead(@PathVariable Long id, @RequestParam String assignedTo) {
        return ResponseEntity.ok(leadService.assignLead(id, assignedTo));
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<LeadResponse> updateLeadStatus(
            @PathVariable Long id,
            @RequestBody String status) {
        return ResponseEntity.ok(leadService.updateLeadStatus(id, status));
    }
}
