package com.shop.order.application.mapper;

import com.shop.order.domain.entity.EventEntity;
import com.shop.order.infrastructure.vo.EventRequest;

import java.time.LocalDateTime;

public class EventMapper {

    public static EventEntity requestToEntity(EventRequest eventRequest) {
        return EventEntity.builder()
                .orderId(eventRequest.orderId())
                .name(eventRequest.name())
                .created(LocalDateTime.now())
                .build();
    }
}
