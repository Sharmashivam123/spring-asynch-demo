package com.ss.spring_asynch_demo.services;

import com.ss.spring_asynch_demo.client.OrderServiceClient;
import com.ss.spring_asynch_demo.client.RestClient;
import com.ss.spring_asynch_demo.records.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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
}
