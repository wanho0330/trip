package com.trip.dashboard.controller;

import com.trip.auth.security.JwtTokenProvider;
import com.trip.common.code.KafkaActions;
import com.trip.dashboard.dto.EventRatio;
import com.trip.dashboard.entity.UserEvent;
import com.trip.dashboard.service.DashboardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DashboardController.class)
class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DashboardService dashboardService;

    @MockitoBean
    private JwtTokenProvider jwtTokenProvider;

    String DASHBOARD_API = "/apiv1/dashboard";


    @WithMockUser(username = "test@test.com")
    @Test
    void getRecentEvents_Success() throws Exception {
        // Given
        List<UserEvent> mockEvents = Arrays.asList(
                UserEvent.builder()
                        .action(KafkaActions.CREATED)
                        .beforeUserIdx(1L)
                        .beforeUserEmail("before@example.com")
                        .afterUserIdx(2L)
                        .afterUserEmail("after@example.com")
                        .timestamp(123456789L)
                        .build(),
                UserEvent.builder()
                        .action(KafkaActions.UPDATED)
                        .beforeUserIdx(2L)
                        .beforeUserEmail("before2@example.com")
                        .afterUserIdx(3L)
                        .afterUserEmail("after2@example.com")
                        .timestamp(987654321L)
                        .build()
        );

        when(dashboardService.getRecentEvents()).thenReturn(mockEvents);

        // When & Then
        mockMvc.perform(get(DASHBOARD_API + "/recent"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userEvents").isArray())
                .andExpect(jsonPath("$.userEvents[0].action").value("CREATED"))
                .andExpect(jsonPath("$.userEvents[1].action").value("UPDATED"));
    }

    @WithMockUser(username = "test@test.com")
    @Test
    void getEventRatio_Success() throws Exception {
        // Given
        EventRatio mockEventRatio = EventRatio.builder()
                .createdRatio(0.5)
                .updatedRatio(0.3)
                .deletedRatio(0.2)
                .build();

        when(dashboardService.getRatioByAction()).thenReturn(mockEventRatio);

        // When & Then
        mockMvc.perform(get(DASHBOARD_API + "/ratio"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.createdRatio").value(0.5))
                .andExpect(jsonPath("$.updatedRatio").value(0.3))
                .andExpect(jsonPath("$.deletedRatio").value(0.2));
    }
}