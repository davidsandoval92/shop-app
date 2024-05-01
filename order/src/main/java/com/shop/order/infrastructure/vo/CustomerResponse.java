package com.shop.order.infrastructure.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CustomerResponse(Long customerId, String firstName, String lastName, String email, LocalDateTime created, LocalDate dateOfBirth) {}
