package com.shop.order.application.impl;

import com.shop.order.application.api.OrderService;
import com.shop.order.application.mapper.OrderMapper;
import com.shop.order.domain.repository.OrderRepository;
import com.shop.order.infrastructure.vo.OrderRequest;
import com.shop.order.infrastructure.vo.OrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        log.info("[OrderServiceImpl] createOrder by order request: " + orderRequest);
        return OrderMapper.entityToResponse(repository.save(OrderMapper.requestToEntity(orderRequest)));
    }

    @Override
    public List<OrderResponse> getOrdersByCustomerId(Long customerId) {
        log.info("[OrderServiceImpl] getOrdersByCustomerId by customer id: " + customerId);
        return repository.findAllByCustomerId(customerId)
                .stream()
                .map(order -> new OrderResponse(order.getOrderId(), order.getCustomerId(), order.getStatus(), order.getCreated(), order.getUpdated()))
                .collect(Collectors.toList());
    }
}
