package com.trip.sync.repository;

import com.trip.sync.entity.ElasticUserEvent;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticRepository extends ElasticsearchRepository<ElasticUserEvent, String> { }
