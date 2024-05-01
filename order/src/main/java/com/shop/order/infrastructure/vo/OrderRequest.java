package com.shop.order.infrastructure.vo;

import java.time.LocalDateTime;

public record OrderRequest(Long customerId, String status, LocalDateTime created, LocalDateTime updated) {
}
