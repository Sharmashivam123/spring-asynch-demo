package com.ss.spring_asynch_demo.controller;

import com.ss.spring_asynch_demo.records.UserProfileResponse;
import com.ss.spring_asynch_demo.services.OrderService;
import com.ss.spring_asynch_demo.services.UserProfileService;
import com.ss.spring_asynch_demo.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-profile")
@AllArgsConstructor
@Slf4j
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/{userid}")
    public UserProfileResponse getUserProfile(@PathVariable(value = "userid") String userid) {
        log.info("Fetching user profile for user ID: {}", userid);
        UserProfileResponse userProfileResponse = userProfileService.getUserProfile(userid);
        log.info("User profile fetched successfully for user ID: {}", userid);

        return userProfileResponse;
    }
}
