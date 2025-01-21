package com.trip.dashboard.repository;

import com.trip.common.code.KafkaActions;
import com.trip.dashboard.entity.UserEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface DashboardRepository extends ElasticsearchRepository<UserEvent, String> {
    List<UserEvent> findAllByOrderByTimestampDesc(Pageable pageable);

    long countByAction(KafkaActions action);
}
