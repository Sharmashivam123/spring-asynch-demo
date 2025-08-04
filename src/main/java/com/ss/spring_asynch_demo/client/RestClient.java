package com.ss.spring_asynch_demo.client;

import com.ss.spring_asynch_demo.records.OrderResponse;
import com.ss.spring_asynch_demo.records.UserInformationResponse;

import java.util.List;

public interface RestClient {
    List<OrderResponse> getOrdersByUserId(String userId);
    UserInformationResponse getUserInformation(String userId);
}
