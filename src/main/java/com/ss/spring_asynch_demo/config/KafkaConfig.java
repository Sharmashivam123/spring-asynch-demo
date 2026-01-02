package com.ss.spring_asynch_demo.config;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

@Configuration
@Slf4j
public class KafkaConfig {

    private final KafkaAdmin kafkaAdmin;

    public KafkaConfig(KafkaAdmin kafkaAdmin) {
        this.kafkaAdmin = kafkaAdmin;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void setupKafka() {
        log.info("Kafka configuration setup completed.");
        // Add Kafka configuration setup logic here
        createTopicIfNotExists("order_processing_topic", 3, (short) 3);
    }

    private void createTopicIfNotExists(String topic, int partition, short replicationFactor) {
        // Logic to create Kafka topic if it does not exist
        log.info("Ensuring topic '{}' exists with {} partitions and replication factor of {}", topic, partition, replicationFactor);
        // This is a placeholder for actual topic creation logic using AdminClient or similar
        try (AdminClient client = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
            NewTopic newTopic = new NewTopic(topic, partition, replicationFactor);
            client.createTopics(Collections.singletonList(newTopic)).all().get();
            log.info("Topic '{}' created or already exists.", topic);
        } catch (ExecutionException e) {
            log.error("Error creating topic '{}': {}", topic, e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
