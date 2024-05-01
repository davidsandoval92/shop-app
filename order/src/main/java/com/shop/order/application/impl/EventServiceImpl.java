package com.shop.order.application.impl;

import com.shop.order.application.api.EventService;
import com.shop.order.application.mapper.EventMapper;
import com.shop.order.domain.entity.EventEntity;
import com.shop.order.domain.repository.EventRepository;
import com.shop.order.infrastructure.vo.EventRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createEvent(EventRequest eventRequest) {
        log.info("[EventServiceImpl] createEvent by event request: " + eventRequest);
        repository.save(EventMapper.requestToEntity(eventRequest));
    }

    @Override
    public Page<EventEntity> findAllEventsByOrderId(Long orderId, Integer pageNumber, Integer pageSize) {
        log.info(String.format("[EventServiceImpl] findAllEventsByOrderId by order id: [%s], page number: [%s] and page size[%s]", orderId, pageNumber, pageSize));
        return repository.findAllByOrderId(orderId, PageRequest.of(pageNumber, pageSize));
    }
}
