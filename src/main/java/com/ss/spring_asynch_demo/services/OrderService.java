package com.ss.spring_asynch_demo.services;

import com.ss.spring_asynch_demo.client.OrderServiceClient;
import com.ss.spring_asynch_demo.client.RestClient;
import com.ss.spring_asynch_demo.exceptions.OrderProcessingException;
import com.ss.spring_asynch_demo.records.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final RestClient restClient;
    private final OrderServiceClient orderServiceClient;

    public Flux<OrderResponse> getOrdersByUserId(String userId) {
        log.info("Fetching order information for user ID: {}", userId);

        return orderServiceClient.getOrdersByUserId(userId);
    }

    @Async
    public void processOrder(String orderId) {
        try {
            log.info("Processing order ID: {} by thread: {}", orderId, Thread.currentThread().getName());
            Mono<String> response = orderServiceClient.processOrder(orderId);

            // Subscribe to the Mono so the WebClient request is actually executed.
            response.subscribe(
                    res -> log.info("Order ID: {} processed successfully! Response: {}", orderId, res),
                    err -> {
                        log.error("Unexpected error while processing order ID {}: {}", orderId, err.getMessage());
                        // Note: throwing inside subscriber won't propagate to the caller; log and handle as needed.
                    }
            );

        } catch (Exception e) {
            log.error("Unexpected error while processing order ID {}: {}", orderId, e.getMessage());
            throw new OrderProcessingException("Failed to process order ID: " + orderId);
        }
    }
}
