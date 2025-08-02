package com.ss.spring_asynch_demo.controller;

import com.ss.spring_asynch_demo.records.EmailRequest;
import com.ss.spring_asynch_demo.services.AsyncService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/notifications")
public class AsyncController {

    private final AsyncService asyncService;

    @PostMapping("/send")
    public String sendNotification(@RequestBody EmailRequest request) {
        for(int i = 0; i<request.to().size(); i++) {
            asyncService.sendNotification(request, i);
        }
        return "Notification sent successfully!";
    }

}
