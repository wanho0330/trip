package com.trip.sync.handler;

import com.trip.common.code.KafkaEntityTypes;
import com.trip.common.kafka.KafkaEvent;

public interface EventHandler<T> {
    boolean supports(KafkaEntityTypes eventType);
    void handle(KafkaEvent<T> event);
}
