package com.trip.common.code;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum KafkaEntityTypes {
    User("user");

    private final String string;
}
