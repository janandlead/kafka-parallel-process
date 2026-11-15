package com.javacodeex.kafka.parallel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** JSON order event consumed by the example. */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private String productCode;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private String status;
    private LocalDateTime createdAt;
}
