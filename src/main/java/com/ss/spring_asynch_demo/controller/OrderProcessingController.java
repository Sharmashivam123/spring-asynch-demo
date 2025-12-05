package com.ss.spring_asynch_demo.controller;

import com.ss.spring_asynch_demo.services.OrderService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process")
@Slf4j
@AllArgsConstructor
public class OrderProcessingController {

    private OrderService orderService;

    @PostMapping("/{orderId}")
    @RateLimiter(name = "order-processor-rate-limiter", fallbackMethod = "orderProcessingRateLimiterFallback")
    public ResponseEntity<ProcessorSuccessResponse> processRequest(@PathVariable String orderId) {
        log.info("Processing request...");
        orderService.processOrder(orderId);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Request processed successfully.");

        return ResponseEntity.ok(new ProcessorSuccessResponse("SUCCESS", "Request processed successfully."));
    }

    public ResponseEntity<ProcessorSuccessResponse> orderProcessingRateLimiterFallback(String orderId, Throwable throwable) {
        log.error("Rate limit exceeded for order ID: {}. Please try again later.", orderId);
        return new ResponseEntity<>(new ProcessorSuccessResponse("429", "Rate limit exceeded. Please try again later."), HttpStatusCode.valueOf(429));
    }

    private class ProcessorSuccessResponse {

        private String status;
        private String message;

        public ProcessorSuccessResponse(String status, String message) {
            this.status = status;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public String getStatus() {
            return status;
        }
    }
}