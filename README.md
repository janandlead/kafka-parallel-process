# Kafka Consumer Group: Parallel Processing

This Spring Boot 3.4.5 / Java 21 example shows how Kafka distributes work across multiple consumers in one consumer group.

## What demonstrates the parallelism?

The listener uses `concurrency=3`, so Spring Kafka starts three consumer threads. The topic is created with three partitions. Kafka assigns each partition to at most one consumer in the group:

```text
orders-parallel (3 partitions)
       ├── partition 0 -> order-consumer-0
       ├── partition 1 -> order-consumer-1
       └── partition 2 -> order-consumer-2
```

Messages in the same partition remain ordered. Messages in different partitions can be processed at the same time. If the topic has fewer partitions than consumers, the extra consumers remain idle.

## Run locally

Prerequisites: Java 21, Maven, and Kafka at `localhost:9092`.

```bash
mvn spring-boot:run
```

The application creates `orders-parallel` with three partitions when the broker is available. Publish several JSON values to that topic, for example:

```json
{
  "id": 1,
  "productCode": "P1001",
  "productName": "Laptop",
  "quantity": 2,
  "price": 50000,
  "status": "CREATED",
  "createdAt": "2026-11-15T10:30:00"
}
```

Check the logs for different listener thread names and partition numbers:

```text
Processed order id=1 on thread=...-C-1 ... partition=0 offset=0
```

## Configuration

| Property | Default | Purpose |
| --- | --- | --- |
| `spring.kafka.bootstrap-servers` | `localhost:9092` | Kafka broker address |
| `spring.kafka.consumer.group-id` | `order-parallel-consumer-group` | Shared group for all listener consumers |
| `app.kafka.topic` | `orders-parallel` | Topic consumed by the listener |
| `app.kafka.partitions` | `3` | Topic partitions created by the app |
| `app.kafka.consumer-concurrency` | `3` | Consumers started by this application instance |

To run two application instances, keep the same group id. Kafka distributes the topic partitions across both instances. Use a different group id when every instance should receive every message.

For a topic that already exists, Kafka will not reduce its partition count. Increase `app.kafka.consumer-concurrency` only when the topic has at least that many partitions and the processing workload benefits from it.
