package com.trip.dashboard.service;

import com.trip.dashboard.dto.EventRatio;
import com.trip.dashboard.entity.UserEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DashboardService {
    List<UserEvent> getRecentEvents();
    EventRatio getRatioByAction();

}
