package com.trip.dashboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@Builder
@RequiredArgsConstructor
public class EventRatio {
    private final double createdRatio;
    private final double updatedRatio;
    private final double deletedRatio;
}
