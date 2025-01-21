package com.trip.sync.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trip.common.code.KafkaTopics;
import com.trip.common.kafka.KafkaEvent;
import com.trip.sync.dto.User;
import com.trip.sync.handler.EventHandler;
import com.trip.sync.handler.EventHandlerRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaConsumer {

    private final ObjectMapper objectMapper;
    private final EventHandlerRegistry eventHandlerRegistry;

    @KafkaListener(topics = KafkaTopics.MEMBER_EVENTS, groupId = "member-event-group")
    public void consume(String message) {
        try {
            KafkaEvent<User> kafkaEvent = objectMapper.readValue(message, new TypeReference<>() {
            });
            EventHandler<User> handler = eventHandlerRegistry.getHandler(kafkaEvent.getEntityType());

            handler.handle(kafkaEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to process Kafka message", e);
        }
    }
}