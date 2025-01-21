package com.trip.dashboard.dto;

import com.trip.dashboard.entity.UserEvent;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class GetRecentEvents {

    @Getter
    @Builder
    public static class Res {
        private List<UserEvent> userEvents;
    }
}
