package com.kafka.kafkademo.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String,String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public void sendMessage(String message, String key, String topic) {
        kafkaTemplate.send(topic, key, message);
    }
}
