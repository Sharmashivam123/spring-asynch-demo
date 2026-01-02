package com.ss.spring_asynch_demo.events;

import com.ss.common_lib.requests.OrderProcessingRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class OrderProducer {

    private static final String TOPIC = "order_processing_topic";

    private final KafkaTemplate<String, OrderProcessingRequest> kafkaTemplate;


    public OrderProducer(KafkaTemplate<String, OrderProcessingRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderProcessingEvent(String orderId, OrderProcessingRequest orderProcessingRequest) {
        log.info("Producing order processing event for order ID: {}", orderId);
        CompletableFuture<SendResult<String, OrderProcessingRequest>> future = kafkaTemplate.send(TOPIC, orderId, orderProcessingRequest);
        future.whenComplete((sendResult, ex) -> {
            if (ex != null) {
                log.error("Failed to send order processing event for order ID: {}. Error: {}", orderId, ex.getMessage());
                throw new RuntimeException("Failed to send order processing event for order ID: " + orderId, ex);
            } else {
                log.info("Successfully sent order processing event for order ID: {} to partition: {}", orderId, sendResult.getRecordMetadata().partition());
            }
        });
    }
}
