package com.shop.order.infrastructure.vo;

import java.time.LocalDateTime;

public record CustomerOrderResponse(Long OrderId, String customerName, String status, LocalDateTime created,
                                    LocalDateTime updated) {
}
