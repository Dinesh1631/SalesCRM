package com.SalsCRM.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.SalsCRM.leadManagement.entity.Lead;

public interface LeadRepository extends JpaRepository<Lead, Long> {
}
