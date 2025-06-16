package com.SalsCRM.service;


import java.util.List;

import com.SalsCRM.leadManagement.dto.LeadRequest;
import com.SalsCRM.leadManagement.dto.LeadResponse;

public interface LeadService {

    LeadResponse createLead(LeadRequest request);
    List<LeadResponse> getAllLeads();
    LeadResponse assignLead(Long id, String assignedTo);
	void deleteLead(Long id);
	LeadResponse updateLead(Long id, LeadRequest request);
	LeadResponse updateLeadStatus(Long id, String status);
}