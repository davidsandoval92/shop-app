package com.shop.order.infrastructure.vo;

import java.time.LocalDateTime;

public record EventResponse(Long eventId, Long orderId, String name, LocalDateTime created) {
}
