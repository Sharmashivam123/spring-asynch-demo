package com.ss.spring_asynch_demo.services;

import com.ss.spring_asynch_demo.records.UserInformationResponse;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UserService {
    public CompletableFuture<UserInformationResponse> getUserInformation(String userId) {
        return CompletableFuture.completedFuture(new UserInformationResponse(
                Long.parseLong(userId),
                "John",
                "Doe",
                "shxxx@gmail.com",
                "123-456-7890"));
    }
}
