package com.javacodeex.kafka.parallel.consumer;

import com.javacodeex.kafka.parallel.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Demonstrates multiple Kafka consumers in the same consumer group.
 * Spring creates three listener-container consumers from the concurrency setting.
 */
@Slf4j
@Component
public class ParallelOrderConsumer {

    @KafkaListener(
            topics = "${app.kafka.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            concurrency = "${app.kafka.consumer-concurrency}",
            clientIdPrefix = "order-consumer"
    )
    public void consume(ConsumerRecord<String, Order> record) {
        Order order = record.value();
        log.info("Processed order id={} on thread={} key={} partition={} offset={}",
                order.getId(), Thread.currentThread().getName(), record.key(),
                record.partition(), record.offset());

        // Put the slow business operation here. Records in one partition stay ordered;
        // different partitions can be processed concurrently by different consumers.
    }
}
