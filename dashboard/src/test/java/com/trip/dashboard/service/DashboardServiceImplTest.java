package com.trip.dashboard.service;

import com.trip.common.code.KafkaActions;
import com.trip.dashboard.dto.EventRatio;
import com.trip.dashboard.entity.UserEvent;
import com.trip.dashboard.repository.DashboardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DashboardServiceImplTest {

    @Mock
    private DashboardRepository dashboardRepository;

    @InjectMocks
    private DashboardServiceImpl dashboardService;

    @Test
    void getRecentEvents_Success() {
        // given
        UserEvent createdUserEvent = UserEvent.builder()
                .action(KafkaActions.CREATED)
                .timestamp(1672531200000L)
                .build();

        UserEvent updatedUserEvent = UserEvent.builder()
                .action(KafkaActions.UPDATED)
                .timestamp(1672531190000L)
                .build();

        List<UserEvent> mockEvents = List.of(createdUserEvent, updatedUserEvent);

        Pageable pageable = PageRequest.of(0, 20);
        when(dashboardRepository.findAllByOrderByTimestampDesc(pageable)).thenReturn(mockEvents);

        // when
        List<UserEvent> recentEvents = dashboardService.getRecentEvents();

        // then
        assertEquals(2, recentEvents.size());
        assertEquals(KafkaActions.CREATED, recentEvents.get(0).getAction());
        assertEquals(KafkaActions.UPDATED, recentEvents.get(1).getAction());
        verify(dashboardRepository, times(1)).findAllByOrderByTimestampDesc(pageable);
    }

    @Test
    void getRatioByAction_Success() {
        // given
        long createdCount = 50L;
        long updatedCount = 30L;
        long deletedCount = 20L;


        when(dashboardRepository.countByAction(KafkaActions.CREATED)).thenReturn(createdCount);
        when(dashboardRepository.countByAction(KafkaActions.UPDATED)).thenReturn(updatedCount);
        when(dashboardRepository.countByAction(KafkaActions.DELETED)).thenReturn(deletedCount);

        // When
        EventRatio eventRatio = dashboardService.getRatioByAction();

        // Then
        assertEquals(50.0, eventRatio.getCreatedRatio(), 0.001);
        assertEquals(30.0, eventRatio.getUpdatedRatio(), 0.001);
        assertEquals(20.0, eventRatio.getDeletedRatio(), 0.001);

        verify(dashboardRepository, times(1)).countByAction(KafkaActions.CREATED);
        verify(dashboardRepository, times(1)).countByAction(KafkaActions.UPDATED);
        verify(dashboardRepository, times(1)).countByAction(KafkaActions.DELETED);
    }
}