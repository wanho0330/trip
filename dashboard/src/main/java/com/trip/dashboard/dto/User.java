package com.trip.dashboard.dto;

import com.trip.common.code.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class User {
    private Long idx;
    private String email;
    private String name;
    private Status status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String lastLoginIp;
    private LocalDateTime lastLoginAt;
    private int failedAttempts;
}
