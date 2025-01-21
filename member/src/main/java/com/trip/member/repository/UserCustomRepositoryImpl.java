package com.trip.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.trip.member.code.Status;
import com.trip.member.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.trip.member.entity.QUserEntity.userEntity;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final JPAQueryFactory queryFactory;


    @Override
    public Optional<UserEntity> findByEmail(String email) {
        UserEntity user = queryFactory.selectFrom(userEntity)
                .where(userEntity.email.eq(email)
                        .and(userEntity.status.ne(Status.DELETED)))
                .fetchOne();

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<UserEntity> findByIdx(Long idx) {
        UserEntity user = queryFactory.selectFrom(userEntity)
                .where(userEntity.idx.eq(idx)
                        .and(userEntity.status.ne(Status.DELETED)))
                .fetchOne();

        return Optional.ofNullable(user);
    }

    @Override
    public boolean existsByIdx(Long idx) {
        return queryFactory.selectOne()
                .from(userEntity)
                .where(userEntity.idx.eq(idx)
                        .and(userEntity.status.ne(Status.DELETED)))
                .fetchFirst() != null;
    }

    @Override
    public List<UserEntity> findByEmailStartsWith(Long cursor, String email, Pageable pageable) {
        return queryFactory.selectFrom(userEntity)
                .where(userEntity.idx.lt(cursor)
                        .and(userEntity.email.startsWith(email))
                        .and(userEntity.status.ne(Status.DELETED)))
                .orderBy(userEntity.idx.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<UserEntity> findByNameStartsWith(Long cursor, String name, Pageable pageable) {
        return queryFactory.selectFrom(userEntity)
                .where(userEntity.idx.lt(cursor)
                        .and(userEntity.name.startsWith(name))
                        .and(userEntity.status.ne(Status.DELETED)))
                .orderBy(userEntity.idx.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<UserEntity> findAllByOrderByIdxDesc(Long cursor, Pageable pageable) {
        return queryFactory.selectFrom(userEntity)
                .where(userEntity.idx.lt(cursor)
                        .and(userEntity.status.ne(Status.DELETED)))
                .orderBy(userEntity.idx.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
