package com.trip.member.repository;

import com.trip.member.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long>, UserCustomRepository {

}