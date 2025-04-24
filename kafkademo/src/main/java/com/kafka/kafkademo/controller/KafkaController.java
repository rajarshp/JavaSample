package com.kafka.kafkademo.controller;

import com.kafka.kafkademo.service.KafkaProducerService;
import com.kafka.kafkademo.service.KafkaTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final KafkaProducerService kafkaService;
    private final KafkaTopicService kafkaTopicService;

    @Autowired
    public KafkaController(KafkaProducerService kafkaService, KafkaTopicService kafkaTopicService){
        this.kafkaService = kafkaService;
        this.kafkaTopicService = kafkaTopicService;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam("message") String message, @RequestParam("key") String key, @RequestParam("topic") String topic){
        kafkaService.sendMessage(message, key, topic);

        return "Message sent";
    }

    @PostMapping("/topic")
    public String createTopic(@RequestParam String topic){
        kafkaTopicService.createTopic(topic);

        return "Topic Created";
    }
}
