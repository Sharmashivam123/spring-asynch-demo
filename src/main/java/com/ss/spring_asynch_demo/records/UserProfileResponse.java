package com.ss.spring_asynch_demo.records;

import java.util.List;

public record UserProfileResponse (UserInformationResponse user, List<OrderResponse> orders) {
    public UserProfileResponse(UserInformationResponse user, List<OrderResponse> orders) {
        this.user = user;
        this.orders = orders;
    }
}