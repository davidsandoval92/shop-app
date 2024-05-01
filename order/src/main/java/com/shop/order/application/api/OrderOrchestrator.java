package com.shop.order.application.api;

import com.shop.order.infrastructure.vo.CustomerOrderResponse;
import com.shop.order.infrastructure.vo.OrderRequest;

import java.util.List;

public interface OrderOrchestrator {

    void createOrder(OrderRequest orderRequest);

    List<CustomerOrderResponse> getOrdersByCustomerId(Long customerId);
}
