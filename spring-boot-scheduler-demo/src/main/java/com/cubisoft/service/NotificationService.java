package com.cubisoft.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

/**
 * NotificationService
 * Handles sending emails (simple and with attachments)
 * Integrated with ReportService to send daily reports
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender mailSender;
    private final ReportService reportService;  // Autowired ReportService

    // ------------------------
    // Send simple notification (no attachment)
    // ------------------------
    public void sendSimpleNotification(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
            log.info("✅ Simple notification sent successfully to {}", to);
        } catch (Exception e) {
            log.error("❌ Failed to send simple notification to {}: {}", to, e.getMessage(), e);
            throw new RuntimeException("Failed to send simple notification", e);
        }
    }

    // ------------------------
    // Send report notification (with Excel attachment)
    // ------------------------
    public void sendReportNotification(String to, String subject, String body) {
        try {
            // Generate professional Excel report
            byte[] reportData = reportService.generateProfessionalOrderReport();
            String attachmentFileName = "orders-report.xlsx";

            // Prepare MIME message with attachment
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);

            ByteArrayResource resource = new ByteArrayResource(reportData);
            helper.addAttachment(attachmentFileName, resource);

            mailSender.send(mimeMessage);
            log.info("✅ Report notification sent successfully to {}", to);

        } catch (MessagingException e) {
            log.error("❌ MessagingException while sending report notification to {}: {}", to, e.getMessage(), e);
            throw new RuntimeException("Failed to send report notification", e);
        } catch (Exception e) {
            log.error("❌ Unexpected error while sending report notification to {}: {}", to, e.getMessage(), e);
            throw new RuntimeException("Failed to send report notification", e);
        }
    }
}
