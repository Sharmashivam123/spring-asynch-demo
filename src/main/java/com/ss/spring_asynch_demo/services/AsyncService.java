package com.ss.spring_asynch_demo.services;

import com.ss.spring_asynch_demo.records.EmailRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AsyncService {

    @Async
    public void sendNotification(EmailRequest request, int index) {
        try {
            // Simulate a long-running task
            log.info("Sending notification to user : "+ request.to().get(index) +" by thread: " + Thread.currentThread().getName());
            Thread.sleep(5000);
            log.info("Notification sent successfully!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Error while sending notification: " + e.getMessage());
        }
    }
}
