package com.trip.sync.handler;

import com.trip.common.code.KafkaEntityTypes;
import com.trip.common.kafka.KafkaEvent;
import com.trip.sync.dto.User;
import com.trip.sync.service.EventService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserEventHandler implements EventHandler<User> {

    private final EventService eventService;
    private static final Logger logger = LoggerFactory.getLogger(UserEventHandler.class);

    @Override
    public boolean supports(KafkaEntityTypes entityType) {
        return entityType.name().equals(KafkaEntityTypes.User.name());
    }

    /**
     * Kafka Event Action 을 확인하여 함수를 호출합니다.
     *
     * @param event 발생한 이벤트
     *              {@code LOGIN}, {@code REFRESH}, ...
     */
    @Override
    public void handle(KafkaEvent<User> event) {
        if (event.getAction() == null) {
            throw new IllegalArgumentException("action is null");
        }

        switch (event.getAction()) {
            case LOGIN:
                eventService.saveLoginEvent(event);
                break;
            case REFRESH:
                eventService.saveRefreshEvent(event);
                break;
            case LOGOUT:
                eventService.saveLogoutEvent(event);
                break;
            case CREATED:
                eventService.saveCreatedEvent(event);
                break;
            case DELETED:
                eventService.saveDeletedEvent(event);
                break;
            case UPDATED:
                eventService.saveUpdatedEvent(event);
                break;
            case GET_ALL_USERS:
                eventService.saveGetAllUsersEvent(event);
                break;
            case GET_USERS_BY_IDX:
                eventService.saveGetUsersByIdxEvent(event);
                break;
            case PASSWORD_UPDATED:
                eventService.savePasswordUpdatedEvent(event);
                break;
            case GET_USERS_BY_NAME:
                eventService.saveGetUsersByNameEvent(event);
                break;
            case GET_USERS_BY_EMAIL:
                eventService.saveGetUsersByEmailEvent(event);
                break;
            default:
                logger.warn("Unknown action");
                throw new IllegalArgumentException("Unknown action");
        }
    }
}
