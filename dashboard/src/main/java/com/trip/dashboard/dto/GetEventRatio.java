package com.trip.dashboard.dto;

import lombok.Builder;
import lombok.Getter;

public class GetEventRatio {

    @Getter
    @Builder
    public static class Res {
        private double createdRatio;
        private double updatedRatio;
        private double deletedRatio;
    }
}
