package com.ss.spring_asynch_demo.services;

import com.ss.spring_asynch_demo.client.RestClient;
import com.ss.spring_asynch_demo.records.UserInformationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final RestClient restClient;

    public CompletableFuture<UserInformationResponse> getUserInformation(String userId) {
        log.info("Fetching user information for user ID: {}", userId);

        return CompletableFuture.supplyAsync(() -> restClient.getUserInformation(userId));
    }
}
