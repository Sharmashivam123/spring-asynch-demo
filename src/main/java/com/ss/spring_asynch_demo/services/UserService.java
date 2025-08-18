package com.ss.spring_asynch_demo.services;

import com.ss.spring_asynch_demo.client.UserServiceClient;
import com.ss.spring_asynch_demo.records.UserInformationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserServiceClient userServiceClient;

    public Mono<UserInformationResponse> getUserInformation(String userId) {
        log.info("Fetching user information for user ID: {}", userId);

        return userServiceClient.getUserInformation(userId);
    }
}
