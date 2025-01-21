package com.trip.sync.service;

import com.trip.common.kafka.KafkaEvent;
import com.trip.sync.dto.User;
import com.trip.sync.repository.ElasticRepository;
import com.trip.sync.repository.MongoRepository;
import com.trip.sync.util.Convert;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final MongoRepository mongoRepository;
    private final ElasticRepository elasticRepository;

    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

    public void saveLoginEvent(KafkaEvent<User> event) {
        logger.info("EventService-saveLoginEvent-{}", event);
        mongoRepository.save(Convert.KafkaToMongo(event));
        elasticRepository.save(Convert.KafkaToElastic(event));
        logger.info("EventService-saveLoginEvent-end");
    }

    public void saveRefreshEvent(KafkaEvent<User> event) {
        logger.info("EventService-saveRefreshEvent-{}", event);
        mongoRepository.save(Convert.KafkaToMongo(event));
        elasticRepository.save(Convert.KafkaToElastic(event));
        logger.info("EventService-saveRefreshEvent-end");

    }

    public void saveLogoutEvent(KafkaEvent<User> event) {
        logger.info("EventService-saveLogoutEvent-{}", event);
        mongoRepository.save(Convert.KafkaToMongo(event));
        elasticRepository.save(Convert.KafkaToElastic(event));
        logger.info("EventService-saveLogoutEvent-end");
    }

    public void saveCreatedEvent(KafkaEvent<User> event) {
        logger.info("EventService-saveCreatedEvent-{}", event);
        mongoRepository.save(Convert.KafkaToMongo(event));
        elasticRepository.save(Convert.KafkaToElastic(event));
        logger.info("EventService-saveCreatedEvent-end");
    }

    public void saveDeletedEvent(KafkaEvent<User> event) {
        logger.info("EventService-saveDeletedEvent-{}", event);
        mongoRepository.save(Convert.KafkaToMongo(event));
        elasticRepository.save(Convert.KafkaToElastic(event));
        logger.info("EventService-saveDeletedEvent-end");
    }

    public void saveUpdatedEvent(KafkaEvent<User> event) {
        logger.info("EventService-saveUpdatedEvent-{}", event);
        mongoRepository.save(Convert.KafkaToMongo(event));
        elasticRepository.save(Convert.KafkaToElastic(event));
        logger.info("EventService-saveUpdatedEvent-end");
    }

    public void saveGetAllUsersEvent(KafkaEvent<User> event) {
        logger.info("EventService-saveGetAllUsersEvent-{}", event);
        mongoRepository.save(Convert.KafkaToMongo(event));
        elasticRepository.save(Convert.KafkaToElastic(event));
        logger.info("EventService-saveGetAllUsersEvent-end");
    }

    public void saveGetUsersByIdxEvent(KafkaEvent<User> event) {
        logger.info("EventService-saveGetUsersByIdxEvent-{}", event);
        mongoRepository.save(Convert.KafkaToMongo(event));
        elasticRepository.save(Convert.KafkaToElastic(event));
        logger.info("EventService-saveGetUsersByIdxEvent-end");
    }

    public void savePasswordUpdatedEvent(KafkaEvent<User> event) {
        logger.info("EventService-savePasswordUpdatedEvent-{}", event);
        mongoRepository.save(Convert.KafkaToMongo(event));
        elasticRepository.save(Convert.KafkaToElastic(event));
        logger.info("EventService-savePasswordUpdatedEvent-end");
    }

    public void saveGetUsersByNameEvent(KafkaEvent<User> event) {
        logger.info("EventService-saveGetUsersByNameEvent-{}", event);
        mongoRepository.save(Convert.KafkaToMongo(event));
        elasticRepository.save(Convert.KafkaToElastic(event));
        logger.info("EventService-saveGetUsersByNameEvent-end");
    }

    public void saveGetUsersByEmailEvent(KafkaEvent<User> event) {
        logger.info("EventService-saveGetUsersByEmailEvent-{}", event);
        mongoRepository.save(Convert.KafkaToMongo(event));
        elasticRepository.save(Convert.KafkaToElastic(event));
        logger.info("EventService-saveGetUsersByEmailEvent-end");
    }
}
