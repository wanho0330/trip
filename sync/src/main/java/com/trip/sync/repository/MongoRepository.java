package com.trip.sync.repository;


import com.trip.sync.entity.MongoUserEvent;

public interface MongoRepository extends org.springframework.data.mongodb.repository.MongoRepository<MongoUserEvent, Long> {
}
