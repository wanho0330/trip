package com.trip.gateway.actuator;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

@Component
public class KafkaHealthIndicator implements HealthIndicator {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Override
    public Health health() {
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        try (AdminClient client = AdminClient.create(config)) {
            client.describeCluster().nodes().get();
            Set<String> topics = client.listTopics().names().get();

            return Health.up()
                    .withDetail("Topics", new ArrayList<>(topics))
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("Kafka", "Unavailable")
                    .withException(e)
                    .build();
        }
    }
}