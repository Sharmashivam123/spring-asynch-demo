package com.ss.spring_asynch_demo.controller;

import com.ss.spring_asynch_demo.records.EmailRequest;
import com.ss.spring_asynch_demo.services.AsyncService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AsyncControllerTest {

    @Test
    void sendNotification() {
        // Arrange
        AsyncService asyncService = mock(AsyncService.class);
        AsyncController controller = new AsyncController(asyncService);

        List<String> recipients = Arrays.asList("user1@example.com", "user2@example.com");
        EmailRequest request = mock(EmailRequest.class);
        when(request.to()).thenReturn(recipients);

        // Act
        String result = controller.sendNotification(request);

        // Assert
        verify(asyncService, times(recipients.size())).sendNotification(request, anyInt());
        assertEquals("Notification sent successfully!", result);
    }
}