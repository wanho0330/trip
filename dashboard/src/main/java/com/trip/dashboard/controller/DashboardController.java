package com.trip.dashboard.controller;

import com.trip.dashboard.dto.EventRatio;
import com.trip.dashboard.dto.GetEventRatio;
import com.trip.dashboard.dto.GetRecentEvents;
import com.trip.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/apiv1/dashboard")
@RestController
public class DashboardController {
    private final DashboardService dashboardService;

    /**
     * 최근 발생한 이벤트를 출력합니다.
     *
     * @return {@code GetRecentEvent.Res} 객체에 담긴 최근 발생한 20개의 이벤트를 포함한 ResponseEntity
     */
    @GetMapping("/recent")
    public ResponseEntity<GetRecentEvents.Res> getRecentEvents() {
        GetRecentEvents.Res res = GetRecentEvents.Res.builder()
                .userEvents(dashboardService.getRecentEvents())
                .build();

        return ResponseEntity.ok(res);
    }

    /**
     * 발생한 이벤트의 Action 비율을 출력합니다.
     *
     * @return {@code GetEventRatio.Res} 객체에 담긴 생성, 업데이트, 삭제 이벤트의 비율을 포함한 ResponseEntity
     */
    @GetMapping("/ratio")
    public ResponseEntity<GetEventRatio.Res> getEventRatio() {

        EventRatio eventRatio = dashboardService.getRatioByAction();

        GetEventRatio.Res res = GetEventRatio.Res.builder()
                .createdRatio(eventRatio.getCreatedRatio())
                .updatedRatio(eventRatio.getUpdatedRatio())
                .deletedRatio(eventRatio.getDeletedRatio())
                .build();

        return ResponseEntity.ok(res);
    }



}
