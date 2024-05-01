package com.shop.order.infrastructure.vo;

import java.time.LocalDateTime;

public record ShipmentResponse(Long shipmentId, Long orderId, String status, LocalDateTime created,
                               LocalDateTime updated) {
}
