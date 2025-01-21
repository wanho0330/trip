# Trip Multi-Module Application

이 프로젝트는 여러 모듈을 통합하여 구성된 멀티모듈 Spring Boot 애플리케이션으로, 회원 관리, API 게이트웨이, 데이터 동기화 등의 기능을 제공합니다. 각 모듈은 독립적인 역할을 수행하며, 전체 시스템의 유기적인 동작을 지원합니다.

## 프로젝트 구성도

![image](https://github.com/user-attachments/assets/multimodule-system-architecture.png)

## 요구사항

- Java Development Kit (JDK) 23 이상
- Gradle (빌드 도구)
- Maven Central Repository 접근 권한 (의존성 다운로드)
- Docker 및 Docker Compose 설치

## 멀티모듈 구성

### 1. Member Application
- 회원 관리 기능 제공.
- Spring Data JPA를 사용하여 데이터베이스와 연동.

### 2. Member Auth Application
- 회원 인증 기능 제공.
- JWT를 이용한 인증 및 권한 관리.
- Redis 기반 캐싱 및 세션 관리.

### 3. Dashboard Application
- 데이터 처리 및 분석 기능 제공.
- Elasticsearch를 사용하여 검색 및 분석.
- RESTful API로 데이터를 제공.

### 4. Gateway Application
- API 게이트웨이 역할 수행.
- Spring Cloud Gateway를 사용한 API 라우팅 및 필터링.
- Spring Boot Actuator로 시스템 상태 모니터링.

### 5. Sync Application
- 데이터 동기화 및 메시지 처리.
- Kafka Consumer를 통해 메시지 처리.
- MongoDB 및 Elasticsearch와 연동하여 데이터 저장.

### 6. Common Module
- 공통 상수 및 공통 코드 관리.
- 공통 예외 처리 관리.

### 7. Config Module
- 설정 파일 관리.

## 주요 기술 스택

- Spring Boot (각 모듈 공통)
    - Web, Security, Data JPA, Data Redis, Data Elasticsearch, Kafka 등
- Redis: 캐싱 및 세션 관리
- Kafka: 메시징 및 이벤트 기반 데이터 동기화
- MongoDB: NoSQL 데이터 저장
- Elasticsearch: 검색 및 데이터 분석
- PostgreSQL: 데이터베이스 관리
- Logback 및 SLF4J: 로깅 및 디버깅
- QueryDSL: 타입 안전한 쿼리 작성
- JUnit 5: 테스트

## Docker Compose 설정

시스템 구성에 필요한 주요 서비스들은 아래의 Docker Compose 설정으로 관리됩니다.

```yaml
version: '3.8'

services:
  mongo:
    image: mongo
    container_name: mongo-container
    volumes:
      - ~/mongo-data:/data/db
    ports:
      - "27017:27017"

  redis:
    image: redis
    container_name: redis-container
    volumes:
      - ~/redis-data:/data
    ports:
      - "6379:6379"
    command: redis-server --save 60 1 --loglevel warning

  elasticsearch:
    image: docker.elastic.co/elasticsearch/elasticsearch:8.10.1
    container_name: elasticsearch-container
    environment:
      - discovery.type=single-node
      - xpack.security.enabled=false # 보안 기능 비활성화 (개발 환경)
    volumes:
      - ~/elastic-data:/usr/share/elasticsearch/data
    ports:
      - "9200:9200"
      - "9300:9300"

  zookeeper:
    image: confluentinc/cp-zookeeper:latest
    container_name: zookeeper-container
    ports:
      - "2181:2181"
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000

  kafka:
    image: confluentinc/cp-kafka:latest
    container_name: kafka-container
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper-container:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092
      KAFKA_LOG_DIRS: /kafka/kafka-logs
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
    volumes:
      - ~/kafka-data:/kafka/kafka-logs
    depends_on:
      - zookeeper

  postgres:
    image: postgres:latest
    container_name: postgres-container
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: app_data
    volumes:
      - ~/postgres-data:/var/lib/postgresql/data
    ports:
      - "5432:5432"
```

### 실행 방법

1. **Docker Compose 실행**
   ```
   docker-compose up -d
   ```

2. **서비스 확인**
    - MongoDB: `localhost:27017`
    - Redis: `localhost:6379`
    - Elasticsearch: `localhost:9200`
    - Kafka: `localhost:9092`
    - PostgreSQL: `localhost:5432`

3. **애플리케이션 빌드 및 실행**
   ```
   ./gradlew clean build
   ./gradlew bootRun
   ```

4. **테스트 실행**
   ```
   ./gradlew test
   ```

5. **QueryDSL 설정**
    - 생성된 QueryDSL QClass 파일은 각 모듈의 `src/main/generated` 디렉토리에 저장됩니다.
    - `clean` 명령 실행 시 `generated` 디렉토리가 자동으로 삭제됩니다.

6. **애플리케이션 모니터링**
    - Spring Boot Actuator를 통해 시스템 상태를 확인할 수 있습니다.
    - 기본 헬스 체크 엔드포인트는 `/actuator/health`입니다.