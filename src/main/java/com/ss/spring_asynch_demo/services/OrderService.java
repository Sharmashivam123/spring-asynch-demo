package com.ss.spring_asynch_demo.services;

import com.ss.spring_asynch_demo.client.RestClient;
import com.ss.spring_asynch_demo.records.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final RestClient restClient;

    public CompletableFuture<List<OrderResponse>> getOrdersByUserId(String userId) {
        log.info("Fetching order information for user ID: {}", userId);

        return CompletableFuture.supplyAsync(() -> restClient.getOrdersByUserId(userId)).exceptionallyCompose((ex)-> {
            log.error("Error fetching orders for user ID: {}", userId, ex);

            return CompletableFuture.completedFuture(List.of());
        });
    }
}
