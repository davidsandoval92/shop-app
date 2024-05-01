package com.shop.order.infrastructure.client;

import com.shop.order.infrastructure.vo.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "CustomerApi", url = "http://customer:8080/ms/customer")
public interface CustomerApiClient {

    @GetMapping("/v1/customer/{customer-id}")
    ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("customer-id") Long customerId);
}
