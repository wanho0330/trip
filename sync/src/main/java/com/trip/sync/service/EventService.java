package com.trip.sync.service;

import com.trip.common.kafka.KafkaEvent;
import com.trip.sync.dto.User;
import org.springframework.stereotype.Service;

@Service
public interface EventService {
    void saveLoginEvent(KafkaEvent<User> kafkaEvent);
    void saveRefreshEvent(KafkaEvent<User> kafkaEvent);
    void saveLogoutEvent(KafkaEvent<User> kafkaEvent);
    void saveCreatedEvent(KafkaEvent<User> kafkaEvent);
    void saveUpdatedEvent(KafkaEvent<User> kafkaEvent);
    void saveDeletedEvent(KafkaEvent<User> kafkaEvent);
    void saveGetAllUsersEvent(KafkaEvent<User> event);
    void saveGetUsersByIdxEvent(KafkaEvent<User> event);
    void savePasswordUpdatedEvent(KafkaEvent<User> event);
    void saveGetUsersByNameEvent(KafkaEvent<User> event);
    void saveGetUsersByEmailEvent(KafkaEvent<User> event);
}
