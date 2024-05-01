package com.shop.customer.infrastructure.controller;

import com.shop.customer.application.api.CustomerService;
import com.shop.customer.infrastructure.vo.CustomerRequest;
import com.shop.customer.infrastructure.vo.CustomerResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

  private final CustomerService service;

  @Autowired
  public CustomerController(CustomerService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Void> createCustomer(@RequestBody final CustomerRequest request) {
    log.info(String.format("Creating customer with information [%s]", request));
    service.createCustomer(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/{customer-id}")
  @CircuitBreaker(name = "CircuitBreakerService")
  public ResponseEntity<CustomerResponse> getCustomerInformation(
      @PathVariable("customer-id") final long customerId) {
    log.info(String.format("Seeking customer by id [%s]", customerId));

    return ResponseEntity.ok(service.findCustomerById(customerId));
  }

  @DeleteMapping("/{customer-id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable("customer-id") final long customerId) {
    log.info(String.format("Deleting customer by id [%s]", customerId));
    service.deleteCustomerById(customerId);
    return ResponseEntity.noContent().build();
  }
}
