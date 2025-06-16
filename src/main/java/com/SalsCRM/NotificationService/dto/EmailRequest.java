package com.SalsCRM.NotificationService.dto;


public class EmailRequest {
    private String to;
    private String leadName;
    private String assignedBy;
    private String assigneeName;

    public EmailRequest() {}

    public EmailRequest(String to, String leadName, String assignedBy, String assigneeName) {
        this.to = to;
        this.leadName = leadName;
        this.assignedBy = assignedBy;
        this.assigneeName = assigneeName;
    }

    // Getters and Setters
    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }

    public String getLeadName() { return leadName; }
    public void setLeadName(String leadName) { this.leadName = leadName; }

    public String getAssignedBy() { return assignedBy; }
    public void setAssignedBy(String assignedBy) { this.assignedBy = assignedBy; }

    public String getAssigneeName() { return assigneeName; }
    public void setAssigneeName(String assigneeName) { this.assigneeName = assigneeName; }
}