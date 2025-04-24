package com.kafka.kafkademo.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "topic2", groupId = "my-group")
    public void consumer1(ConsumerRecord<String, String> record) {
        System.out.println("Consumer 1: Received message: " + record.value() + " from partition: " + record.partition());
    }

    @KafkaListener(topics = "topic2", groupId = "my-group")
    public void consumer2(ConsumerRecord<String, String> record) {
        System.out.println("Consumer 2: Received message: " + record.value() + " from partition: " + record.partition());
    }

    @KafkaListener(topics = "topic2", groupId = "my-group")
    public void consumer3(ConsumerRecord<String, String> record) {
        System.out.println("Consumer 3: Received message: " + record.value() + " from partition: " + record.partition());
    }
}