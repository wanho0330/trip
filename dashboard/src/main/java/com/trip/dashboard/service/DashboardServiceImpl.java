package com.trip.dashboard.service;

import com.trip.common.code.KafkaActions;
import com.trip.dashboard.dto.EventRatio;
import com.trip.dashboard.entity.UserEvent;
import com.trip.dashboard.repository.DashboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DashboardServiceImpl implements DashboardService {

    private final DashboardRepository dashboardRepository;

    @Override
    public List<UserEvent> getRecentEvents() {
        Pageable pageable = PageRequest.of(0, 20);

        return dashboardRepository.findAllByOrderByTimestampDesc(pageable);
    }

    @Override
    public EventRatio getRatioByAction() {

        long createdCount = dashboardRepository.countByAction(KafkaActions.CREATED);
        long updatedCount = dashboardRepository.countByAction(KafkaActions.UPDATED);
        long deletedCount = dashboardRepository.countByAction(KafkaActions.DELETED);

        long total = createdCount + updatedCount + deletedCount;

        double createdRatio = (double) createdCount / total * 100;
        double updatedRatio = (double) updatedCount / total * 100;
        double deletedRatio = (double) deletedCount / total * 100;

        return EventRatio.builder()
                .createdRatio(createdRatio)
                .updatedRatio(updatedRatio)
                .deletedRatio(deletedRatio)
                .build();

    }
}
