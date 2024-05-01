package com.shop.order.infrastructure.vo;

import java.time.LocalDateTime;

public record OrderResponse(Long OrderId, Long customerId, String status, LocalDateTime created,
                            LocalDateTime updated) {
}
