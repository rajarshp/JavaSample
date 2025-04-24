package com.kafka.kafkademo.service;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaTopicService {

    @Autowired
    private KafkaAdmin kafkaAdmin;

    public void createTopic(String topic) {
        try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
            NewTopic newTopic = new NewTopic(topic, 3, (short) 1);
            adminClient.createTopics(Collections.singleton(newTopic)).all().get();

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to create topic: ", e);
        }
    }
}