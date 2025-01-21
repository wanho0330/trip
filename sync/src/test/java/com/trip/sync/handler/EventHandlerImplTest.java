package com.trip.sync.handler;

import com.trip.common.kafka.KafkaEvent;
import com.trip.common.code.KafkaActions;
import com.trip.sync.service.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventHandlerImplTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private UserEventHandler eventHandler;

    @Test
    void handle_LoginEvent_Success() {
        KafkaEvent event = KafkaEvent.builder()
                .action(KafkaActions.LOGIN)
                .timestamp(LocalDateTime.now())
                .build();

        eventHandler.handle(event);

        verify(eventService, times(1)).saveLoginEvent(event);
        verifyNoMoreInteractions(eventService);
    }

    @Test
    void handle_RefreshEvent_Success() {
        KafkaEvent event = KafkaEvent.builder()
                .action(KafkaActions.REFRESH)
                .timestamp(LocalDateTime.now())
                .build();

        eventHandler.handle(event);

        verify(eventService, times(1)).saveRefreshEvent(event);
        verifyNoMoreInteractions(eventService);

    }

    @Test
    void handle_LogoutEvent_Success() {
        KafkaEvent event = KafkaEvent.builder()
                .action(KafkaActions.LOGOUT)
                .timestamp(LocalDateTime.now())
                .build();

        eventHandler.handle(event);

        verify(eventService, times(1)).saveLogoutEvent(event);
        verifyNoMoreInteractions(eventService);

    }

    @Test
    void handle_CreatedEvent_Success() {
        KafkaEvent event = KafkaEvent.builder()
                .action(KafkaActions.CREATED)
                .timestamp(LocalDateTime.now())
                .build();

        eventHandler.handle(event);

        verify(eventService, times(1)).saveCreatedEvent(event);
        verifyNoMoreInteractions(eventService);

    }

    @Test
    void handle_DeletedEvent_Success() {
        KafkaEvent event = KafkaEvent.builder()
                .action(KafkaActions.DELETED)
                .timestamp(LocalDateTime.now())
                .build();

        eventHandler.handle(event);

        verify(eventService, times(1)).saveDeletedEvent(event);
        verifyNoMoreInteractions(eventService);

    }

    @Test
    void handle_UpdatedEvent_Success() {
        KafkaEvent event = KafkaEvent.builder()
                .action(KafkaActions.UPDATED)
                .timestamp(LocalDateTime.now())
                .build();

        eventHandler.handle(event);

        verify(eventService, times(1)).saveUpdatedEvent(event);
        verifyNoMoreInteractions(eventService);

    }

    @Test
    void handle_GetAllUsersEvent_Success() {
        KafkaEvent event = KafkaEvent.builder()
                .action(KafkaActions.GET_ALL_USERS)
                .timestamp(LocalDateTime.now())
                .build();

       eventHandler.handle(event);

        verify(eventService, times(1)).saveGetAllUsersEvent(event);
        verifyNoMoreInteractions(eventService);

    }

    @Test
    void handle_GetUsersByIdxEvent_Success() {
        KafkaEvent event = KafkaEvent.builder()
                .action(KafkaActions.GET_USERS_BY_IDX)
                .timestamp(LocalDateTime.now())
                .build();

        eventHandler.handle(event);

        verify(eventService, times(1)).saveGetUsersByIdxEvent(event);
        verifyNoMoreInteractions(eventService);

    }

    @Test
    void handle_PasswordUpdatedEvent_Success() {
        KafkaEvent event = KafkaEvent.builder()
                .action(KafkaActions.PASSWORD_UPDATED)
                .timestamp(LocalDateTime.now())
                .build();

     eventHandler.handle(event);

        verify(eventService, times(1)).savePasswordUpdatedEvent(event);
        verifyNoMoreInteractions(eventService);

    }

    @Test
    void handle_GetUsersByNameEvent_Success() {
        KafkaEvent event = KafkaEvent.builder()
                .action(KafkaActions.GET_USERS_BY_NAME)
                .timestamp(LocalDateTime.now())
                .build();

        eventHandler.handle(event);

        verify(eventService, times(1)).saveGetUsersByNameEvent(event);
        verifyNoMoreInteractions(eventService);
    }

    @Test
    void handle_GetUsersByEmailEvent_Success() {
        KafkaEvent event = KafkaEvent.builder()
                .action(KafkaActions.GET_USERS_BY_EMAIL)
                .timestamp(LocalDateTime.now())
                .build();

        eventHandler.handle(event);

        verify(eventService, times(1)).saveGetUsersByEmailEvent(event);
        verifyNoMoreInteractions(eventService);
    }

    @Test
    void handle_NullAction_Success() {
        KafkaEvent event = KafkaEvent.builder()
                .action(null)
                .timestamp(LocalDateTime.now())
                .build();

        assertThrows(IllegalArgumentException.class, () -> eventHandler.handle(event));
    }
}