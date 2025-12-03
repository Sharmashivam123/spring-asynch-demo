package com.ss.spring_asynch_demo.client;

import com.ss.spring_asynch_demo.config.DiscoveryApplicationProperties;
import com.ss.spring_asynch_demo.records.UserInformationResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.ss.spring_asynch_demo.utils.constants.AppConstants.USER_INFO_URI;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceClient {

    private final DiscoveryApplicationProperties discoveryApplicationProperties;
    private final WebClient webClient;

    public Mono<UserInformationResponse> getUserInformation(String userId) {
        log.info("Fetching user information for user ID: {}", userId);
        String url = discoveryApplicationProperties.getUserServiceUrl() + USER_INFO_URI;

        return webClient.get()
                .uri(url, userId)
                .retrieve()
                .bodyToMono(UserInformationResponse.class);
    }
}
