package com.example.MyBookStore.kafka;

import com.example.MyBookStore.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@RequiredArgsConstructor()
@Slf4j
public class KafkaPublisher {

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    public void sendMessage(Order order,
                            String topicName) {
        log.info("Publishing on topic:{}",topicName);
        kafkaTemplate.send(topicName, order);
    }

}
