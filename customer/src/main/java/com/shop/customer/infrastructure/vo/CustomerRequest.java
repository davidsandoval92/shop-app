package com.shop.customer.infrastructure.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CustomerRequest(String firstName, String lastName, String email, LocalDateTime created, LocalDate dateOfBirth) {}
