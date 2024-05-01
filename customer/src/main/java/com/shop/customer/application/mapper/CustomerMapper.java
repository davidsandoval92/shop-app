package com.shop.customer.application.mapper;

import com.shop.customer.domain.entity.CustomerEntity;
import com.shop.customer.infrastructure.vo.CustomerRequest;
import com.shop.customer.infrastructure.vo.CustomerResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class CustomerMapper {

  public static CustomerResponse entityToResponse(CustomerEntity entity) {
    return new CustomerResponse(
        entity.getCustomerId(),
        entity.getFirstName(),
        entity.getLastName(),
        entity.getEmail(),
        entity.getCreated(),
        entity.getDateOfBirth());
  }

  public static CustomerEntity requestToEntity(CustomerRequest request) {
    return CustomerEntity.builder()
        .firstName(request.firstName())
        .lastName(request.lastName())
        .email(request.email())
        .created(request.created())
        .dateOfBirth(request.dateOfBirth())
        .created(Objects.nonNull(request.created()) ? request.created() : LocalDateTime.now())
        .build();
  }
}
