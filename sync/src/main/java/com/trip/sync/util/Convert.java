package com.trip.sync.util;

import com.trip.sync.dto.User;
import com.trip.sync.entity.ElasticUserEvent;
import com.trip.sync.entity.MongoUserEvent;

import com.trip.common.kafka.KafkaEvent;

public class Convert {
    public static MongoUserEvent KafkaToMongo(KafkaEvent<User> kafkaEvent) {
        MongoUserEvent.MongoUserEventBuilder builder = MongoUserEvent.builder()
                .action(kafkaEvent.getAction())
                .searchKeyword(kafkaEvent.getSearchKeyword())
                .timestamp(kafkaEvent.getTimestamp());

        if (kafkaEvent.getBefore() != null) {
            builder.beforeUserIdx(kafkaEvent.getBefore().getIdx())
                    .beforeUserEmail(kafkaEvent.getBefore().getEmail())
                    .beforeUserName(kafkaEvent.getBefore().getName())
                    .beforeUserStatus(kafkaEvent.getBefore().getStatus())
                    .beforeUserCreateAt(kafkaEvent.getBefore().getCreateAt())
                    .beforeUserUpdateAt(kafkaEvent.getBefore().getUpdateAt())
                    .beforeUserLastLoginIp(kafkaEvent.getBefore().getLastLoginIp())
                    .beforeUserLastLoginAt(kafkaEvent.getBefore().getLastLoginAt())
                    .beforeUserFailedAttempts(kafkaEvent.getBefore().getFailedAttempts());
        }

        if (kafkaEvent.getAfter() != null) {
            builder.afterUserIdx(kafkaEvent.getAfter().getIdx())
                    .afterUserEmail(kafkaEvent.getAfter().getEmail())
                    .afterUserName(kafkaEvent.getAfter().getName())
                    .afterUserStatus(kafkaEvent.getAfter().getStatus())
                    .afterUserCreateAt(kafkaEvent.getAfter().getCreateAt())
                    .afterUserUpdateAt(kafkaEvent.getAfter().getUpdateAt())
                    .afterUserLastLoginIp(kafkaEvent.getAfter().getLastLoginIp())
                    .afterUserLastLoginAt(kafkaEvent.getAfter().getLastLoginAt())
                    .afterUserFailedAttempts(kafkaEvent.getAfter().getFailedAttempts());
        }

        return builder.build();
    }

    public static ElasticUserEvent KafkaToElastic(KafkaEvent<User> kafkaEvent) {
        ElasticUserEvent.ElasticUserEventBuilder builder = ElasticUserEvent.builder()
                .action(kafkaEvent.getAction())
                .searchKeyword(kafkaEvent.getSearchKeyword())
                .timestamp(kafkaEvent.getTimestamp());

        if (kafkaEvent.getBefore() != null) {
            builder.beforeUserIdx(kafkaEvent.getBefore().getIdx())
                    .beforeUserEmail(kafkaEvent.getBefore().getEmail())
                    .beforeUserName(kafkaEvent.getBefore().getName())
                    .beforeUserStatus(kafkaEvent.getBefore().getStatus())
                    .beforeUserCreateAt(kafkaEvent.getBefore().getCreateAt())
                    .beforeUserUpdateAt(kafkaEvent.getBefore().getUpdateAt())
                    .beforeUserLastLoginIp(kafkaEvent.getBefore().getLastLoginIp())
                    .beforeUserLastLoginAt(kafkaEvent.getBefore().getLastLoginAt())
                    .beforeUserFailedAttempts(kafkaEvent.getBefore().getFailedAttempts());
        }

        if (kafkaEvent.getAfter() != null) {
            builder.afterUserIdx(kafkaEvent.getAfter().getIdx())
                    .afterUserEmail(kafkaEvent.getAfter().getEmail())
                    .afterUserName(kafkaEvent.getAfter().getName())
                    .afterUserStatus(kafkaEvent.getAfter().getStatus())
                    .afterUserCreateAt(kafkaEvent.getAfter().getCreateAt())
                    .afterUserUpdateAt(kafkaEvent.getAfter().getUpdateAt())
                    .afterUserLastLoginIp(kafkaEvent.getAfter().getLastLoginIp())
                    .afterUserLastLoginAt(kafkaEvent.getAfter().getLastLoginAt())
                    .afterUserFailedAttempts(kafkaEvent.getAfter().getFailedAttempts());
        }

        return builder.build();
    }

}
