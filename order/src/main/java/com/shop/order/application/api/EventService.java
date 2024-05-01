package com.shop.order.application.api;

import com.shop.order.domain.entity.EventEntity;
import com.shop.order.infrastructure.vo.EventRequest;
import org.springframework.data.domain.Page;

public interface EventService {

    void createEvent(EventRequest eventRequest);

    Page<EventEntity> findAllEventsByOrderId(Long orderId, Integer pageNumber, Integer pageSize);
}
