package com.trip.sync.entity;

import com.trip.common.code.KafkaActions;
import com.trip.common.code.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Document(indexName = "user_event")
public class ElasticUserEvent {
    /**
     * document 고유 인덱스
     */
    @Id
    private String idx;
    /**
     * 발생한 유저 이벤트
     * {@code CREATED}: 유저 생성, {@code GET_ALL_USERS}: 모든 유저 조회, {@code GET_USERS_BY_IDX}: 고유 인덱스에 해당하는 유저 조회 ...
     */
    private KafkaActions action;
    /**
     * 이벤트가 발생하기 전 유저 고유 인덱스
     * 이벤트에 따라 존재하지 않을 경우 0
     */
    private Long beforeUserIdx;
    /**
     * 이벤트가 발생하기 전 유저 이메일
     * 이벤트에 따라 존재하지 않음
     */
    private String beforeUserEmail;
    /**
     * 이벤트가 발생하기 전 유저 이름.
     * 이벤트에 따라 존재하지 않음.
     */
    private String beforeUserName;

    /**
     * 이벤트가 발생하기 전 유저 상태.
     * 이벤트에 따라 존재하지 않음.
     * {@code ACTIVE}: 활성화, {@code INACTIVE}: 비활성화
     */
    @Enumerated(EnumType.STRING)
    private Status beforeUserStatus;

    /**
     * 이벤트가 발생하기 전 유저 생성 시간.
     * 이벤트에 따라 존재하지 않음.
     */
    private LocalDateTime beforeUserCreateAt;

    /**
     * 이벤트가 발생하기 전 유저 업데이트 시간.
     * 이벤트에 따라 존재하지 않음.
     */
    private LocalDateTime beforeUserUpdateAt;

    /**
     * 이벤트가 발생하기 전 유저 마지막 로그인 IP.
     * 이벤트에 따라 존재하지 않음.
     */
    private String beforeUserLastLoginIp;

    /**
     * 이벤트가 발생하기 전 유저 마지막 로그인 시간.
     * 이벤트에 따라 존재하지 않음.
     */
    private LocalDateTime beforeUserLastLoginAt;

    /**
     * 이벤트가 발생하기 전 유저 로그인 실패 횟수.
     * 이벤트에 따라 존재하지 않음.
     */
    private int beforeUserFailedAttempts;

    /**
     * 이벤트가 발생한 후 유저 고유 인덱스.
     * 이벤트에 따라 존재하지 않을 경우 0.
     */
    private Long afterUserIdx;

    /**
     * 이벤트가 발생한 후 유저 이메일.
     * 이벤트에 따라 존재하지 않음.
     */
    private String afterUserEmail;

    /**
     * 이벤트가 발생한 후 유저 이름.
     * 이벤트에 따라 존재하지 않음.
     */
    private String afterUserName;

    /**
     * 이벤트가 발생한 후 유저 상태.
     * 이벤트에 따라 존재하지 않음.
     */
    @Enumerated(EnumType.STRING)
    private Status afterUserStatus;

    /**
     * 이벤트가 발생한 후 유저 생성 시간.
     * 이벤트에 따라 존재하지 않음.
     */
    private LocalDateTime afterUserCreateAt;

    /**
     * 이벤트가 발생한 후 유저 업데이트 시간.
     * 이벤트에 따라 존재하지 않음.
     */
    private LocalDateTime afterUserUpdateAt;

    /**
     * 이벤트가 발생한 후 유저 마지막 로그인 IP.
     * 이벤트에 따라 존재하지 않음.
     */
    private String afterUserLastLoginIp;

    /**
     * 이벤트가 발생한 후 유저 마지막 로그인 시간.
     * 이벤트에 따라 존재하지 않음.
     */
    private LocalDateTime afterUserLastLoginAt;

    /**
     * 이벤트가 발생한 후 유저 로그인 실패 횟수.
     * 이벤트에 따라 존재하지 않음.
     */
    private int afterUserFailedAttempts;

    /**
     * email 또는 name으로 유저를 검색할 경우 사용되는 검색어.
     */
    private String searchKeyword;

    /**
     * document가 생성된 시간.
     */
    private LocalDateTime timestamp;

    @Builder
    public ElasticUserEvent(
            KafkaActions action,
            Long beforeUserIdx,
            String beforeUserEmail,
            String beforeUserName,
            Status beforeUserStatus,
            LocalDateTime beforeUserCreateAt,
            LocalDateTime beforeUserUpdateAt,
            String beforeUserLastLoginIp,
            LocalDateTime beforeUserLastLoginAt,
            int beforeUserFailedAttempts,
            Long afterUserIdx,
            String afterUserEmail,
            String afterUserName,
            Status afterUserStatus,
            LocalDateTime afterUserCreateAt,
            LocalDateTime afterUserUpdateAt,
            String afterUserLastLoginIp,
            LocalDateTime afterUserLastLoginAt,
            int afterUserFailedAttempts,
            String searchKeyword,
            LocalDateTime timestamp
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
