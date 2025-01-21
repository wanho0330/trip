package com.trip.dashboard.entity;

import com.trip.common.code.KafkaActions;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Document(indexName = "user_event")
public class UserEvent {
    /**
     * document 고유 인덱스
     */
    @Id
    private String idx;

    /**
     * Kafka에서 발생한 사용자 액션
     */
    private KafkaActions action;

    /**
     * 이벤트가 발생하기 전 유저 고유 인덱스.
     * 이벤트에 따라 존재하지 않을 경우 {@code 0}.
     */
    private Long beforeUserIdx;

    /**
     * 이벤트가 발생하기 전 유저 이메일.
     * 이벤트에 따라 존재하지 않을 수 있음.
     */
    private String beforeUserEmail;

    /**
     * 이벤트가 발생하기 전 유저 이름.
     * 이벤트에 따라 존재하지 않을 수 있음.
     */
    private String beforeUserName;

    /**
     * 이벤트가 발생하기 전 유저 상태.
     * 이벤트에 따라 존재하지 않을 수 있음.
     */
    private String beforeUserStatus;

    /**
     * 이벤트가 발생하기 전 유저 생성 시간 (타임스탬프).
     * 이벤트에 따라 존재하지 않을 수 있음.
     */
    private Long beforeUserCreateAt;

    /**
     * 이벤트가 발생하기 전 유저 업데이트 시간 (타임스탬프).
     * 이벤트에 따라 존재하지 않을 수 있음.
     */
    private Long beforeUserUpdateAt;

    /**
     * 이벤트가 발생하기 전 유저 마지막 로그인 IP.
     * 이벤트에 따라 존재하지 않을 수 있음.
     */
    private String beforeUserLastLoginIp;

    /**
     * 이벤트가 발생하기 전 유저 마지막 로그인 시간 (타임스탬프).
     * 이벤트에 따라 존재하지 않을 수 있음.
     */
    private Long beforeUserLastLoginAt;

    /**
     * 이벤트가 발생하기 전 유저 로그인 실패 횟수.
     * 이벤트에 따라 존재하지 않을 수 있음.
     */
    private int beforeUserFailedAttempts;

    /**
     * 이벤트가 발생한 후 유저 고유 인덱스.
     * 이벤트에 따라 존재하지 않을 경우 {@code 0}.
     */
    private Long afterUserIdx;

    /**
     * 이벤트가 발생한 후 유저 이메일.
     * 이벤트에 따라 존재하지 않을 수 있음.
     */
    private String afterUserEmail;

    /**
     * 이벤트가 발생한 후 유저 이름.
     * 이벤트에 따라 존재하지 않을 수 있음.
     */
    private String afterUserName;

    /**
     * 이벤트가 발생한 후 유저 상태.
     * 이벤트에 따라 존재하지 않을 수 있음.
     */
    private String afterUserStatus;

    /**
     * 이벤트가 발생한 후 유저 생성 시간 (타임스탬프).
     * 이벤트에 따라 존재하지 않을 수 있음.
     */
    private Long afterUserCreateAt;

    /**
     * 이벤트가 발생한 후 유저 업데이트 시간 (타임스탬프).
     * 이벤트에 따라 존재하지 않을 수 있음.
     */
    private Long afterUserUpdateAt;

    /**
     * 이벤트가 발생한 후 유저 마지막 로그인 IP.
     * 이벤트에 따라 존재하지 않을 수 있음.
     */
    private String afterUserLastLoginIp;

    /**
     * 이벤트가 발생한 후 유저 마지막 로그인 시간 (타임스탬프).
     * 이벤트에 따라 존재하지 않을 수 있음.
     */
    private Long afterUserLastLoginAt;

    /**
     * 이벤트가 발생한 후 유저 로그인 실패 횟수.
     * 이벤트에 따라 존재하지 않을 수 있음.
     */
    private int afterUserFailedAttempts;

    /**
     * email 또는 name을 기준으로 유저를 검색할 경우의 검색어.
     */
    private String searchKeyword;

    /**
     * document가 생성된 시간 (타임스탬프).
     */
    private Long timestamp;

    @Builder
    public UserEvent(
            KafkaActions action,
            Long beforeUserIdx,
            String beforeUserEmail,
            String beforeUserName,
            String beforeUserStatus,
            Long beforeUserCreateAt,
            Long beforeUserUpdateAt,
            String beforeUserLastLoginIp,
            Long beforeUserLastLoginAt,
            int beforeUserFailedAttempts,
            Long afterUserIdx,
            String afterUserEmail,
            String afterUserName,
            String afterUserStatus,
            Long afterUserCreateAt,
            Long afterUserUpdateAt,
            String afterUserLastLoginIp,
            Long afterUserLastLoginAt,
            int afterUserFailedAttempts,
            String searchKeyword,
            Long timestamp
    ) {
        this.idx = UUID.randomUUID().toString();
        this.action = action;
        this.beforeUserIdx = beforeUserIdx;
        this.beforeUserEmail = beforeUserEmail;
        this.beforeUserName = beforeUserName;
        this.beforeUserStatus = beforeUserStatus;
        this.beforeUserCreateAt = beforeUserCreateAt;
        this.beforeUserUpdateAt = beforeUserUpdateAt;
        this.beforeUserLastLoginIp = beforeUserLastLoginIp;
        this.beforeUserLastLoginAt = beforeUserLastLoginAt;
        this.beforeUserFailedAttempts = beforeUserFailedAttempts;
        this.afterUserIdx = afterUserIdx;
        this.afterUserEmail = afterUserEmail;
        this.afterUserName = afterUserName;
        this.afterUserStatus = afterUserStatus;
        this.afterUserCreateAt = afterUserCreateAt;
        this.afterUserUpdateAt = afterUserUpdateAt;
        this.afterUserLastLoginIp = afterUserLastLoginIp;
        this.afterUserLastLoginAt = afterUserLastLoginAt;
        this.afterUserFailedAttempts = afterUserFailedAttempts;
        this.searchKeyword = searchKeyword;
        this.timestamp = timestamp;
    }
}
