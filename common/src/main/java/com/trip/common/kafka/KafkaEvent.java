package com.trip.common.kafka;

import com.trip.common.code.KafkaActions;
import com.trip.common.code.KafkaEntityTypes;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class KafkaEvent<T> {
    private KafkaActions action;
    private KafkaEntityTypes entityType;
    private T before;
    private T after;
    private String searchKeyword;
    private LocalDateTime timestamp;
}
