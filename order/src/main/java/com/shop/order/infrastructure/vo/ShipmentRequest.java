package com.shop.order.infrastructure.vo;

import java.time.LocalDateTime;

public record ShipmentRequest(Long orderId, String status, LocalDateTime created, LocalDateTime updated) {
}
