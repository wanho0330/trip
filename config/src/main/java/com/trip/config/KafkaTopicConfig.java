package com.trip.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    public static final String MEMBER_EVENTS = "MEMBER_EVENTS";

    @Bean
    public NewTopic createTopic() {
        return new NewTopic(MEMBER_EVENTS, 1, (short) 1);
    }
}
