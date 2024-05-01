package com.shop.order.application.api;

import com.shop.order.infrastructure.vo.OrderRequest;
import com.shop.order.infrastructure.vo.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest orderRequest);

    List<OrderResponse> getOrdersByCustomerId(Long customerId);
}
