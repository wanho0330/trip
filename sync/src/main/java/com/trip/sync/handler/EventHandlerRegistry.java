package com.trip.sync.handler;

import com.trip.common.code.KafkaEntityTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class EventHandlerRegistry {
    private final List<EventHandler<?>> handlers;

    @SuppressWarnings("unchecked")
    public <T> EventHandler<T> getHandler(KafkaEntityTypes entityType) {
        return (EventHandler<T>) handlers.stream()
                .filter(handler -> handler.supports(entityType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No handler found for entity type: " + entityType));
    }
}