package com.ss.spring_asynch_demo.client;

import com.ss.spring_asynch_demo.config.DiscoveryApplicationProperties;
import com.ss.spring_asynch_demo.records.OrderResponse;
import com.ss.spring_asynch_demo.records.UserInformationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.ss.spring_asynch_demo.utils.constants.AppConstants.ORDERS_BY_USER_ID_URI;
import static com.ss.spring_asynch_demo.utils.constants.AppConstants.USER_INFO_URI;

@Service
@AllArgsConstructor
public class RestClientImpl implements RestClient {

    private final RestTemplate restTemplate;
    private final DiscoveryApplicationProperties discoveryApplicationProperties;

    public List<OrderResponse> getOrdersByUserId(String userId) {
        String url = discoveryApplicationProperties.getOrderServiceUrl() + ORDERS_BY_USER_ID_URI;
        OrderResponse[] orders = restTemplate.getForObject(url, OrderResponse[].class, userId);

        return List.of(orders);
    }

    public UserInformationResponse getUserInformation(String userId) {
        String url = discoveryApplicationProperties.getUserServiceUrl() + USER_INFO_URI;

        return restTemplate.getForObject(url, UserInformationResponse.class, userId);
    }

}
