package com.ss.spring_asynch_demo.services;

import com.ss.spring_asynch_demo.records.OrderResponse;
import com.ss.spring_asynch_demo.records.UserInformationResponse;
import com.ss.spring_asynch_demo.records.UserProfileResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class UserProfileService {
    private final UserService userService;
    private final OrderService orderService;

    // Method to get user profile details
    public UserProfileResponse getUserProfile(String userId) {
        CompletableFuture<UserInformationResponse> user = userService.getUserInformation(userId);
        CompletableFuture<List<OrderResponse>> orders = orderService.getOrdersByUserId(userId);


        return CompletableFuture.allOf(user, orders)
                .thenApplyAsync(v -> new UserProfileResponse(user.join(), orders.join())).join();
    }
}
