package com.trip.auth.service;

import com.trip.auth.kafka.AuthKafkaProducer;
import com.trip.auth.security.JwtTokenProvider;
import com.trip.auth.dto.AuthToken;
import com.trip.auth.entity.TokenEntity;
import com.trip.auth.exception.ExpiredTokenException;
import com.trip.auth.exception.InvalidTokenException;
import com.trip.auth.repository.AuthRepository;
import com.trip.common.code.KafkaActions;
import com.trip.common.code.KafkaEntityTypes;
import com.trip.common.kafka.KafkaEvent;
import com.trip.member.dto.User;
import com.trip.member.entity.UserEntity;
import com.trip.common.exception.UserNotFoundException;
import com.trip.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthRepository authRepository;

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthKafkaProducer authKafkaProducer;

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Override
    public AuthToken loginUser(String email, String password) {
        logger.info("AuthService-loginUser-email={}", email);
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

        User user = User.formEntity(userEntity);
        String userPassword = userEntity.getPassword();

        if (user == null || !userPassword.equals(password)) {
            logger.info("AuthService-loginUser-user password is incorrect");
            throw new UserNotFoundException();
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user.getIdx());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getIdx());

        TokenEntity tokenEntity = TokenEntity.builder()
                .token(refreshToken)
                .idx(user.getIdx().toString())
                .build();

        authRepository.save(tokenEntity);

        AuthToken authToken = AuthToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        KafkaEvent<User> kafkaEvent = KafkaEvent.<User>builder()
                .action(KafkaActions.LOGIN)
                .entityType(KafkaEntityTypes.User)
                .after(user)
                .timestamp(LocalDateTime.now())
                .build();

        authKafkaProducer.send(kafkaEvent);

        logger.info("AuthService-loginUser-accessToken={}", accessToken);
        return authToken;
    }

    @Override
    public AuthToken refreshAccessToken(String refreshToken) {
        logger.info("AuthService-refreshAccessToken-refreshToken={}", refreshToken);

        if (!authRepository.existsByRefreshToken(refreshToken)) {
            logger.info("AuthService-refreshAccessToken-refreshToken is incorrect");
            throw new ExpiredTokenException("Expired token");
        }

        TokenEntity tokenEntity = authRepository.findByRefreshToken(refreshToken);
        if (tokenEntity.getIdx() == null) {
            logger.info("AuthService-refreshAccessToken-user not found");
            throw new InvalidTokenException("Invalid token");
        }
        Long idx = Long.valueOf(tokenEntity.getIdx());

        String accessToken = jwtTokenProvider.generateAccessToken(idx);

        UserEntity userEntity = userRepository.findByIdx(idx).orElseThrow(UserNotFoundException::new);
        User user = User.formEntity(userEntity);

        AuthToken newAccessToken = AuthToken.builder()
                .accessToken(accessToken)
                .build();

        KafkaEvent<User> kafkaEvent = KafkaEvent.<User>builder()
                .action(KafkaActions.REFRESH)
                .entityType(KafkaEntityTypes.User)
                .before(user)
                .after(user)
                .timestamp(LocalDateTime.now())
                .build();

        authKafkaProducer.send(kafkaEvent);

        logger.info("AuthService-refreshAccessToken-accessToken={}", accessToken);
        return newAccessToken;
    }

    @Override
    public void logoutUser(String refreshToken) {
        logger.info("AuthService-logoutUser-refreshToken={}", refreshToken);

        if (!authRepository.existsByRefreshToken(refreshToken)) {
            logger.info("AuthService-logoutUser-refreshToken is incorrect");
            throw new ExpiredTokenException("Expired token");
        }

        TokenEntity tokenEntity = authRepository.findByRefreshToken(refreshToken);
        if (tokenEntity.getIdx() == null) {
            logger.info("AuthService-logoutUser-user not found");
            throw new InvalidTokenException("Invalid token");
        }
        Long idx = Long.valueOf(tokenEntity.getIdx());
        authRepository.delete(tokenEntity);

        UserEntity userEntity = userRepository.findByIdx(idx).orElseThrow(UserNotFoundException::new);
        User user = User.formEntity(userEntity);
        KafkaEvent<User> kafkaEvent = KafkaEvent.<User>builder()
                .action(KafkaActions.LOGOUT)
                .entityType(KafkaEntityTypes.User)
                .before(user)
                .timestamp(LocalDateTime.now())
                .build();

        authKafkaProducer.send(kafkaEvent);
        logger.info("AuthService-logoutUser-token is deleted");
    }



}
