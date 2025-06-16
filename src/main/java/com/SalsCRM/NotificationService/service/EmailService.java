//package com.SalsCRM.NotificationService.service;
//
//
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//
//    private final JavaMailSender mailSender;
//
//    public EmailService(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }
//
//    public void sendSimpleEmail(String to, String subject, String body) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("dineshgolla969@gmail.com");
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(body);
//        mailSender.send(message);
//    }
//}
package com.SalsCRM.NotificationService.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Send HTML-formatted email asynchronously
     */
    @Async
    public void sendLeadAssignmentEmail(String to, String leadName, String assignedBy, String assigneeName) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject("🚀 New Lead Assigned: " + leadName);

            String html = buildLeadAssignmentEmail(leadName, assignedBy, assigneeName);

            helper.setText(html, true); // true = HTML
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // Consider logging
        }
    }

    private String buildLeadAssignmentEmail(String leadName, String assignedBy, String assigneeName) {
        return "<html><body style='font-family:Arial;'>"
                + "<div style='border:1px solid #ddd; padding:15px; border-radius:8px;'>"
                + "<h2 style='color:#4CAF50;'>New Lead Assigned!</h2>"
                + "<p>Hello <b>" + assigneeName + "</b>,</p>"
                + "<p>The lead <b>" + leadName + "</b> has been assigned to you by <b>" + assignedBy + "</b>.</p>"
                + "<p>Please follow up promptly via the Sales CRM dashboard.</p>"
                + "<br><p style='font-size: 12px; color: gray;'>— Sales CRM Notification</p>"
                + "</div></body></html>";
    }
}
