package com.shop.order.application.impl;

import com.shop.order.application.api.EventService;
import com.shop.order.application.api.OrderOrchestrator;
import com.shop.order.application.api.OrderService;
import com.shop.order.application.api.ShipmentService;
import com.shop.order.application.mapper.OrderMapper;
import com.shop.order.application.mapper.ShipmentMapper;
import com.shop.order.domain.entity.ShipmentEntity;
import com.shop.order.infrastructure.client.CustomerApiClient;
import com.shop.order.infrastructure.vo.CustomerOrderResponse;
import com.shop.order.infrastructure.vo.CustomerResponse;
import com.shop.order.infrastructure.vo.EventRequest;
import com.shop.order.infrastructure.vo.OrderRequest;
import com.shop.order.infrastructure.vo.OrderResponse;
import com.shop.order.infrastructure.vo.ShipmentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderOrchestratorImpl implements OrderOrchestrator {

    private final OrderService orderService;

    private final EventService eventService;

    private final ShipmentService shipmentService;

    private final CustomerApiClient customerApiClient;


    @Autowired
    public OrderOrchestratorImpl(OrderService orderService, EventService eventService, ShipmentService shipmentService, CustomerApiClient customerApiClient) {
        this.orderService = orderService;
        this.eventService = eventService;
        this.shipmentService = shipmentService;
        this.customerApiClient = customerApiClient;
    }

    @Override
    public void createOrder(OrderRequest orderRequest) {
        OrderResponse order = orderService.createOrder(orderRequest);
        eventService.createEvent(new EventRequest(order.OrderId(), "order received"));
        eventService.createEvent(new EventRequest(order.OrderId(), "order approved"));
        ShipmentResponse shipment = shipmentService.createShipment(ShipmentEntity.builder().order(OrderMapper.responseToEntity(order)).status("accepted").created(LocalDateTime.now()).updated(LocalDateTime.now()).build());
        eventService.createEvent(new EventRequest(order.OrderId(), "shipment accepted"));
        shipmentService.updateShipmentStatus(ShipmentEntity.builder().shipmentId(shipment.shipmentId()).order(OrderMapper.responseToEntity(order)).status("shipment delivered").created(LocalDateTime.now()).updated(LocalDateTime.now()).build());
        eventService.createEvent(new EventRequest(order.OrderId(), "shipment delivered"));

    }

    @Override
    public List<CustomerOrderResponse> getOrdersByCustomerId(Long customerId) {
        List<OrderResponse> orders = orderService.getOrdersByCustomerId(customerId);
        CustomerResponse customer = customerApiClient.getCustomerById(customerId).getBody();
        return orders.stream()
                .map(order -> new CustomerOrderResponse(order.OrderId(), customer.firstName().concat(" ").concat(customer.lastName()), order.status(), order.created(), order.updated()))
                .collect(Collectors.toList());
    }
}
