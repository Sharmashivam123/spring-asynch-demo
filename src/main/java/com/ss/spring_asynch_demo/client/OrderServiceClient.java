package com.ss.spring_asynch_demo.client;

import com.ss.spring_asynch_demo.config.DiscoveryApplicationProperties;
import com.ss.spring_asynch_demo.records.OrderResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static com.ss.spring_asynch_demo.utils.constants.AppConstants.ORDERS_BY_USER_ID_URI;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceClient {

    private final DiscoveryApplicationProperties discoveryApplicationProperties;
    private final WebClient webClient;

    @CircuitBreaker(name = "order-service-circuit-breaker", fallbackMethod = "getOrdersFallback")
    @Retry(name = "order-service-retry", fallbackMethod = "getOrdersFallbackRetry")
    @TimeLimiter(name = "order-service-timeout")
    public Flux<OrderResponse> getOrdersByUserId(String userId) {
        log.info("Fetching order information for user ID: {}", userId);
        String url = discoveryApplicationProperties.getOrderServiceUrl() + ORDERS_BY_USER_ID_URI;

        return webClient.get()
                .uri(url, userId)
                .retrieve()
                .bodyToFlux(OrderResponse.class);
    }

    private Flux<OrderResponse> getOrdersFallback(String userId, Throwable throwable) {
        log.error("Error fetching orders for user ID: {}. Returning empty list.", userId);
        return Flux.empty();
    }

    private Flux<OrderResponse> getOrdersFallbackRetry(String userId, Throwable throwable) {
        log.error("Retry attempts exhausted for user ID: {}. Returning empty list.", userId);
        return Flux.empty();
    }
}
