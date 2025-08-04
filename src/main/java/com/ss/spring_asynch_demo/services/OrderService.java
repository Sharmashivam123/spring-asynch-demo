package com.ss.spring_asynch_demo.services;

import com.ss.spring_asynch_demo.records.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderService {
    public CompletableFuture<List<OrderResponse>> getOrdersByUserId(String userId) {
        // Simulate fetching orders from a database or external service
        return CompletableFuture.completedFuture(List.of(
                new OrderResponse(1L, "Product A", 2, 29.99),
                new OrderResponse(2L, "Product B", 1, 49.99)
        ));
    }
}
