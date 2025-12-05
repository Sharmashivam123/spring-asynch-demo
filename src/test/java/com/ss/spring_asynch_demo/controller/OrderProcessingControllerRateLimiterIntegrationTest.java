package com.ss.spring_asynch_demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderProcessingControllerRateLimiterIntegrationTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void whenTwoRapidCalls_thenSecondIsRejectedByRateLimiter_and_metricsAreRegistered() throws Exception {
        String url1 = "http://localhost:" + port + "/process/200";
        String url2 = "http://localhost:" + port + "/process/201";

        // First call should be accepted (200)
        ResponseEntity<String> first = restTemplate.postForEntity(url1, null, String.class);
        assertEquals(200, first.getStatusCodeValue(), "First call should succeed");

        // Second call immediately should be rejected by the rate limiter (429)
        ResponseEntity<String> second = restTemplate.postForEntity(url2, null, String.class);
        assertEquals(429, second.getStatusCodeValue(), "Second call should be rate-limited (429)");

        // Query actuator metrics for resilience4j.ratelimiter.calls for our rate limiter name
        String metricUrl = "http://localhost:" + port + "/actuator/metrics/resilience4j.ratelimiter.calls?tag=name:order-processor-rate-limiter";
        ResponseEntity<Map> metricResponse = restTemplate.getForEntity(metricUrl, Map.class);

        // Basic checks on the metrics response - ensure measurements exist
        assertEquals(200, metricResponse.getStatusCodeValue(), "Metrics endpoint should return 200");
        Map body = metricResponse.getBody();
        assertTrue(body != null && body.containsKey("measurements"), "Metric response should contain 'measurements'");

        List measurements = (List) body.get("measurements");
        assertTrue(measurements.size() > 0, "There should be at least one measurement for resilience4j.ratelimiter.calls");

        // Measurement is typically a map with 'statistic' and 'value'
        Map firstMeasurement = (Map) measurements.get(0);
        Object valueObj = firstMeasurement.get("value");
        assertTrue(valueObj instanceof Number, "Metric measurement value should be numeric");
        double value = ((Number) valueObj).doubleValue();
        assertTrue(value >= 0.0, "Metric measurement value should be >= 0");
    }
}
