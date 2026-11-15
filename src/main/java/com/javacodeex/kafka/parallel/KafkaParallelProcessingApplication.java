package com.javacodeex.kafka.parallel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Starts the Kafka consumer-group parallel processing example. */
@SpringBootApplication
public class KafkaParallelProcessingApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaParallelProcessingApplication.class, args);
    }
}
