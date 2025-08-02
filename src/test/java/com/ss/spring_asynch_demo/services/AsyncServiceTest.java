package com.ss.spring_asynch_demo.services;

import com.ss.spring_asynch_demo.records.EmailRequest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AsyncServiceTest {

    @Test
    void sendNotification() {
        AsyncService asyncService = new AsyncService();

        List<String> recipients = Arrays.asList("user1@example.com", "user2@example.com");
        EmailRequest request = mock(EmailRequest.class);
        when(request.to()).thenReturn(recipients);

        // Call the async method (in real scenarios, you may want to use @SpringBootTest and await)
        assertDoesNotThrow(()->asyncService.sendNotification(request, 0));

    }
}