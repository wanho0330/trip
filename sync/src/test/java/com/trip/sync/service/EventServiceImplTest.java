package com.trip.sync.service;

import com.trip.common.kafka.KafkaEvent;
import com.trip.common.code.KafkaActions;
import com.trip.sync.entity.ElasticUserEvent;
import com.trip.sync.entity.MongoUserEvent;
import com.trip.sync.repository.ElasticRepository;
import com.trip.sync.repository.MongoRepository;
import com.trip.sync.util.Convert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    @Mock
    private MongoRepository mongoRepository;

    @Mock
    private ElasticRepository elasticRepository;

    @InjectMocks
    private EventServiceImpl eventService;
    private KafkaEvent createKafkaEvent(KafkaActions action) {
        return KafkaEvent.builder()
                .action(action)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Test
    void saveLoginEvent_Success() {
        // Given
        KafkaEvent event = createKafkaEvent(KafkaActions.LOGIN);
        MongoUserEvent mongoEntity = Convert.KafkaToMongo(event);
        ElasticUserEvent elasticEntity = Convert.KafkaToElastic(event);

        when(mongoRepository.save(any(MongoUserEvent.class))).thenReturn(mongoEntity);
        when(elasticRepository.save(any(ElasticUserEvent.class))).thenReturn(elasticEntity);

        // When
        eventService.saveLoginEvent(event);

        // Then
        verify(mongoRepository, times(1)).save(any(MongoUserEvent.class));
        verify(elasticRepository, times(1)).save(any(ElasticUserEvent.class));
    }

    @Test
    void saveRefreshEvent_Success() {
        // Given
        KafkaEvent event = createKafkaEvent(KafkaActions.REFRESH);
        MongoUserEvent mongoEntity = Convert.KafkaToMongo(event);
        ElasticUserEvent elasticEntity = Convert.KafkaToElastic(event);

        when(mongoRepository.save(any(MongoUserEvent.class))).thenReturn(mongoEntity);
        when(elasticRepository.save(any(ElasticUserEvent.class))).thenReturn(elasticEntity);

        // When
        eventService.saveRefreshEvent(event);

        // Then
        verify(mongoRepository, times(1)).save(any(MongoUserEvent.class));
        verify(elasticRepository, times(1)).save(any(ElasticUserEvent.class));
    }

    @Test
    void saveLogoutEvent_Success() {
        // Given
        KafkaEvent event = createKafkaEvent(KafkaActions.LOGOUT);
        MongoUserEvent mongoEntity = Convert.KafkaToMongo(event);
        ElasticUserEvent elasticEntity = Convert.KafkaToElastic(event);

        when(mongoRepository.save(any(MongoUserEvent.class))).thenReturn(mongoEntity);
        when(elasticRepository.save(any(ElasticUserEvent.class))).thenReturn(elasticEntity);

        // When
        eventService.saveLogoutEvent(event);

        // Then
        verify(mongoRepository, times(1)).save(any(MongoUserEvent.class));
        verify(elasticRepository, times(1)).save(any(ElasticUserEvent.class));
    }

    @Test
    void saveCreatedEvent_Success() {
        // Given
        KafkaEvent event = createKafkaEvent(KafkaActions.CREATED);
        MongoUserEvent mongoEntity = Convert.KafkaToMongo(event);
        ElasticUserEvent elasticEntity = Convert.KafkaToElastic(event);

        when(mongoRepository.save(any(MongoUserEvent.class))).thenReturn(mongoEntity);
        when(elasticRepository.save(any(ElasticUserEvent.class))).thenReturn(elasticEntity);


        // When
        eventService.saveCreatedEvent(event);

        // Then
        verify(mongoRepository, times(1)).save(any(MongoUserEvent.class));
        verify(elasticRepository, times(1)).save(any(ElasticUserEvent.class));
    }

    @Test
    void saveDeletedEvent_Success() {
        // Given
        KafkaEvent event = createKafkaEvent(KafkaActions.DELETED);
        MongoUserEvent mongoEntity = Convert.KafkaToMongo(event);
        ElasticUserEvent elasticEntity = Convert.KafkaToElastic(event);

        when(mongoRepository.save(any(MongoUserEvent.class))).thenReturn(mongoEntity);
        when(elasticRepository.save(any(ElasticUserEvent.class))).thenReturn(elasticEntity);

        // When
        eventService.saveDeletedEvent(event);

        // Then
        verify(mongoRepository, times(1)).save(any(MongoUserEvent.class));
        verify(elasticRepository, times(1)).save(any(ElasticUserEvent.class));
    }

    @Test
    void saveUpdatedEvent_Success() {
        // Given
        KafkaEvent event = createKafkaEvent(KafkaActions.UPDATED);
        MongoUserEvent mongoEntity = Convert.KafkaToMongo(event);
        ElasticUserEvent elasticEntity = Convert.KafkaToElastic(event);

        when(mongoRepository.save(any(MongoUserEvent.class))).thenReturn(mongoEntity);
        when(elasticRepository.save(any(ElasticUserEvent.class))).thenReturn(elasticEntity);

        // When
        eventService.saveUpdatedEvent(event);

        // Then
        verify(mongoRepository, times(1)).save(any(MongoUserEvent.class));
        verify(elasticRepository, times(1)).save(any(ElasticUserEvent.class));
    }

    @Test
    void saveGetAllUsersEvent_Success() {
        // Given
        KafkaEvent event = createKafkaEvent(KafkaActions.GET_ALL_USERS);
        MongoUserEvent mongoEntity = Convert.KafkaToMongo(event);
        ElasticUserEvent elasticEntity = Convert.KafkaToElastic(event);

        when(mongoRepository.save(any(MongoUserEvent.class))).thenReturn(mongoEntity);
        when(elasticRepository.save(any(ElasticUserEvent.class))).thenReturn(elasticEntity);

        // When
        eventService.saveGetAllUsersEvent(event);

        // Then
        verify(mongoRepository, times(1)).save(any(MongoUserEvent.class));
        verify(elasticRepository, times(1)).save(any(ElasticUserEvent.class));
    }

    @Test
    void saveGetUsersByIdxEvent_Success() {
        // Given
        KafkaEvent event = createKafkaEvent(KafkaActions.GET_USERS_BY_IDX);
        MongoUserEvent mongoEntity = Convert.KafkaToMongo(event);
        ElasticUserEvent elasticEntity = Convert.KafkaToElastic(event);

        when(mongoRepository.save(any(MongoUserEvent.class))).thenReturn(mongoEntity);
        when(elasticRepository.save(any(ElasticUserEvent.class))).thenReturn(elasticEntity);


        // When
        eventService.saveGetUsersByIdxEvent(event);

        // Then
        verify(mongoRepository, times(1)).save(any(MongoUserEvent.class));
        verify(elasticRepository, times(1)).save(any(ElasticUserEvent.class));
    }

    @Test
    void savePasswordUpdatedEvent_Success() {
        // Given
        KafkaEvent event = createKafkaEvent(KafkaActions.PASSWORD_UPDATED);
        MongoUserEvent mongoEntity = Convert.KafkaToMongo(event);
        ElasticUserEvent elasticEntity = Convert.KafkaToElastic(event);

        when(mongoRepository.save(any(MongoUserEvent.class))).thenReturn(mongoEntity);
        when(elasticRepository.save(any(ElasticUserEvent.class))).thenReturn(elasticEntity);


        // When
        eventService.savePasswordUpdatedEvent(event);

        // Then
        verify(mongoRepository, times(1)).save(any(MongoUserEvent.class));
        verify(elasticRepository, times(1)).save(any(ElasticUserEvent.class));
    }

    @Test
    void saveGetUsersByNameEvent_Success() {
        // Given
        KafkaEvent event = createKafkaEvent(KafkaActions.GET_USERS_BY_NAME);
        MongoUserEvent mongoEntity = Convert.KafkaToMongo(event);
        ElasticUserEvent elasticEntity = Convert.KafkaToElastic(event);

        when(mongoRepository.save(any(MongoUserEvent.class))).thenReturn(mongoEntity);
        when(elasticRepository.save(any(ElasticUserEvent.class))).thenReturn(elasticEntity);


        // When
        eventService.saveGetUsersByNameEvent(event);

        // Then
        verify(mongoRepository, times(1)).save(any(MongoUserEvent.class));
        verify(elasticRepository, times(1)).save(any(ElasticUserEvent.class));
    }

    @Test
    void saveGetUsersByEmailEvent_Success() {
        // Given
        KafkaEvent event = createKafkaEvent(KafkaActions.GET_USERS_BY_EMAIL);
        MongoUserEvent mongoEntity = Convert.KafkaToMongo(event);
        ElasticUserEvent elasticEntity = Convert.KafkaToElastic(event);

        when(mongoRepository.save(any(MongoUserEvent.class))).thenReturn(mongoEntity);
        when(elasticRepository.save(any(ElasticUserEvent.class))).thenReturn(elasticEntity);


        // When
        eventService.saveGetUsersByEmailEvent(event);

        // Then
        verify(mongoRepository, times(1)).save(any(MongoUserEvent.class));
        verify(elasticRepository, times(1)).save(any(ElasticUserEvent.class));
    }
}