package com.shop.order.infrastructure.controller;

import com.shop.order.application.api.EventService;
import com.shop.order.domain.entity.EventEntity;
import com.shop.order.infrastructure.vo.EventRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/event")
public class EventController {

    private final EventService service;


    @Autowired
    public EventController(final EventService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody final EventRequest request) {
        log.info(String.format("Creating event with information [%s]", request));
        service.createEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/order/{order-id}")
    public ResponseEntity<Page<EventEntity>> getCustomerInformation(
            @PathVariable("order-id") final long orderId,
            @RequestParam final int pageNumber,
            @RequestParam final int pageSize) {
        log.info(String.format("Seeking events by order id [%s]", orderId));

        return ResponseEntity.ok(service.findAllEventsByOrderId(orderId, pageNumber, pageSize));
    }
}
