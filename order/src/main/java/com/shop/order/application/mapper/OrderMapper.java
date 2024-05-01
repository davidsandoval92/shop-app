package com.shop.order.application.mapper;

import com.shop.order.domain.entity.OrderEntity;
import com.shop.order.infrastructure.vo.OrderRequest;
import com.shop.order.infrastructure.vo.OrderResponse;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrderMapper {

    public static OrderEntity requestToEntity(OrderRequest request) {
        return OrderEntity.builder()
                .customerId(request.customerId())
                .status(Objects.nonNull(request.status()) ? request.status() : "received")
                .created(Objects.nonNull(request.created()) ? request.created() : LocalDateTime.now())
                .updated(Objects.nonNull(request.updated()) ? request.updated() : LocalDateTime.now())
                .build();
    }

    public static OrderResponse entityToResponse(OrderEntity entity) {
        return new OrderResponse(entity.getOrderId(), entity.getCustomerId(), entity.getStatus(), entity.getCreated(), entity.getUpdated());
    }
}
