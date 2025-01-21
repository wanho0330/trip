package com.trip.auth.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trip.common.code.KafkaTopics;
import com.trip.common.kafka.KafkaEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void send(KafkaEvent<?> kafkaEvent) {
        try {
            String json = objectMapper.writeValueAsString(kafkaEvent);
            kafkaTemplate.send(KafkaTopics.MEMBER_EVENTS, json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
