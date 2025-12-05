package com.ss.spring_asynch_demo.controller;

import com.ss.spring_asynch_demo.services.OrderService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderProcessingControllerTest {

    @Test
    void processRequest_callsOrderService_and_returnsSuccess() throws Exception {
        // Arrange
        OrderService mockService = mock(OrderService.class);
        CountDownLatch latch = new CountDownLatch(1);

        // when the async method is called, count down the latch so the test can wait for it
        doAnswer(invocation -> {
            // simulate async invocation: count down immediately
            latch.countDown();
            return null;
        }).when(mockService).processOrder("42");

        OrderProcessingController controller = new OrderProcessingController(mockService);

        // Act
        Object response = controller.processRequest("42");

        // Wait up to 3 seconds for the async call to be invoked
        boolean invoked = latch.await(3, TimeUnit.SECONDS);

        // Assert - verify service called
        assertTrue(invoked, "Timed out waiting for processOrder to be invoked");
        verify(mockService, times(1)).processOrder("42");
        assertNotNull(response);

        // The controller returns a private inner class instance; use reflection to read fields via getters
        try {
            var getStatus = response.getClass().getMethod("getStatus");
            var getMessage = response.getClass().getMethod("getMessage");
            String status = (String) getStatus.invoke(response);
            String message = (String) getMessage.invoke(response);

            assertEquals("SUCCESS", status);
            assertEquals("Request processed successfully.", message);
        } catch (Exception e) {
            fail("Failed to inspect response via reflection: " + e.getMessage());
        }
    }

    @Test
    void orderProcessingRateLimiterFallback_returns429Message() {
        // Arrange
        OrderService mockService = mock(OrderService.class);
        OrderProcessingController controller = new OrderProcessingController(mockService);

        // Act
        Object response = controller.orderProcessingRateLimiterFallback("99", new RuntimeException("rate-limit"));

        // Assert
        assertNotNull(response);
        try {
            var getStatus = response.getClass().getMethod("getStatus");
            var getMessage = response.getClass().getMethod("getMessage");
            String status = (String) getStatus.invoke(response);
            String message = (String) getMessage.invoke(response);

            assertEquals("429", status);
            assertEquals("Rate limit exceeded. Please try again later.", message);
        } catch (Exception e) {
            fail("Failed to inspect fallback response via reflection: " + e.getMessage());
        }
    }
}