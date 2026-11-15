package com.javacodeex.kafka.parallel.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/** Creates a topic with enough partitions to demonstrate concurrent consumers. */
@Configuration
public class KafkaTopicConfig {

    @Bean
    NewTopic ordersTopic(
            @Value("${app.kafka.topic}") String topic,
            @Value("${app.kafka.partitions}") int partitions,
            @Value("${app.kafka.replication-factor}") short replicationFactor) {
        return TopicBuilder.name(topic)
                .partitions(partitions)
                .replicas(replicationFactor)
                .build();
    }
}
