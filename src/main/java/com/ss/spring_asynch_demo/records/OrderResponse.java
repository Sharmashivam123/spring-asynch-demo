package com.ss.spring_asynch_demo.records;

public record OrderResponse(
    Long orderId,
    String productName,
    Integer quantity,
    Double price
) {
}
