package com.SalsCRM.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.SalsCRM.leadManagement.dto.LeadRequest;
import com.SalsCRM.leadManagement.dto.LeadResponse;
import com.SalsCRM.leadManagement.entity.Lead;
import com.SalsCRM.leadManagement.exception.LeadNotFoundException;
import com.SalsCRM.repository.LeadRepository;

@Service
public class LeadServiceImpl implements LeadService {

    private final LeadRepository leadRepository;

    public LeadServiceImpl(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    private LeadResponse mapToResponse(Lead lead) {
        LeadResponse response = new LeadResponse();
        response.setId(lead.getId());
        response.setName(lead.getName());
        response.setEmail(lead.getEmail());
        response.setPhone(lead.getPhone());
        response.setStatus(lead.getStatus());
        response.setAssignedTo(lead.getAssignedTo());
        return response;
    }

    @Override
    public LeadResponse createLead(LeadRequest request) {
        Lead lead = new Lead(
            request.getName(),
            request.getEmail(),
            request.getPhone(),
            request.getStatus()
        );
        lead.setAssignedTo(request.getAssignedTo());
        return mapToResponse(leadRepository.save(lead));
    }

    @Override
    public List<LeadResponse> getAllLeads() {
        return leadRepository.findAll()
            .stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public LeadResponse updateLead(Long id, LeadRequest request) {
        Lead lead = leadRepository.findById(id)
            .orElseThrow(() -> new LeadNotFoundException("Lead Not Found with given id :"+id));

        lead.setName(request.getName());
        lead.setEmail(request.getEmail());
        lead.setPhone(request.getPhone());
        lead.setStatus(request.getStatus());
        lead.setAssignedTo(request.getAssignedTo());

        return mapToResponse(leadRepository.save(lead));
    }
    @Override
    public LeadResponse updateLeadStatus(Long id, String status) {
        Lead lead = leadRepository.findById(id)
            .orElseThrow(() -> new LeadNotFoundException("Lead Not Found with given id :"+id));
        
        lead.setStatus(status);
        return mapToResponse(leadRepository.save(lead));
    }

    @Override
    public void deleteLead(Long id) {
        if (!leadRepository.existsById(id)) {
            throw new LeadNotFoundException("Lead Not Found with given id :"+id);
        }
        leadRepository.deleteById(id);
    }

    @Override
    public LeadResponse assignLead(Long id, String assignedTo) {
        Lead lead = leadRepository.findById(id)
            .orElseThrow(() -> new LeadNotFoundException("Lead Not Found with given id :"+id));

        lead.setAssignedTo(assignedTo);
        return mapToResponse(leadRepository.save(lead));
    }
}
