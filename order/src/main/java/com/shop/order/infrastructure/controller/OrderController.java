package com.shop.order.infrastructure.controller;

import com.shop.order.application.api.OrderOrchestrator;
import com.shop.order.infrastructure.vo.CustomerOrderResponse;
import com.shop.order.infrastructure.vo.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/order")
public class OrderController {

    private final OrderOrchestrator orderOrchestrator;

    @Autowired
    public OrderController(OrderOrchestrator orderOrchestrator) {
        this.orderOrchestrator = orderOrchestrator;
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody final OrderRequest request) {
        log.info(String.format("Creating order with information [%s]", request));
        orderOrchestrator.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/customer/{customer-id}")
    public ResponseEntity<List<CustomerOrderResponse>> getOrdersByCustomerId(@PathVariable("customer-id") final long customerId) {
        log.info(String.format("Seeking orders by customer id[%s]", customerId));
        return ResponseEntity.ok(orderOrchestrator.getOrdersByCustomerId(customerId));
    }
}
