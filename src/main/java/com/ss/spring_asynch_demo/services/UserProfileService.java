package com.ss.spring_asynch_demo.services;

import com.ss.spring_asynch_demo.records.OrderResponse;
import com.ss.spring_asynch_demo.records.UserInformationResponse;
import com.ss.spring_asynch_demo.records.UserProfileResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserProfileService {
    private final UserService userService;
    private final OrderService orderService;

    // Method to get user profile details
    public Mono<UserProfileResponse> getUserProfile(String userId) {
        Mono<UserInformationResponse> user = userService.getUserInformation(userId);
        Flux<OrderResponse> orders = orderService.getOrdersByUserId(userId);

        return Mono.zip(user, orders.collectList(), UserProfileResponse::new);
    }
}
