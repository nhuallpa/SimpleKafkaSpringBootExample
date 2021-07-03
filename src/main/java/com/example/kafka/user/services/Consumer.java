package com.example.kafka.user.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @KafkaListener(topics = "my_topic", groupId = "group_id")
    private void consume(String message) throws IOException {
        logger.info(String.format("### -> Consumed message -> %s", message));
    }
}
