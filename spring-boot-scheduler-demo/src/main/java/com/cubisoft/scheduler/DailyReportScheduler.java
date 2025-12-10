package com.cubisoft.scheduler;

import com.cubisoft.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DailyReportScheduler {

    private final NotificationService notificationService;

    // -----------------------------
    // Cron Expression: 10:15 PM daily
    // -----------------------------
    // second, minute, hour, day of month, month, day of week
    // 0 15 22 * * ? -> At 22:15:00 every day
    @Scheduled(cron = "0 15 22 * * ?", zone = "Asia/Kolkata")  // Adjust timezone if needed
    public void sendDailyOrderReport() {
        try {
            String recipient = "recipient@example.com";
            String subject = "Daily Order Report";
            String body = "Hello,\n\nPlease find attached today's order report.\n\nRegards,\nOrder System";

            log.info("📢 Starting scheduled task: Sending daily order report to {}", recipient);
            notificationService.sendReportNotification(recipient, subject, body);
            log.info("✅ Scheduled task completed: Report sent successfully.");

        } catch (Exception e) {
            log.error("❌ Error in scheduled task while sending daily order report: {}", e.getMessage(), e);
        }
    }
}
