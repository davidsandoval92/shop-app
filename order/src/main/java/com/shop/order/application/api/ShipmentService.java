package com.shop.order.application.api;

import com.shop.order.infrastructure.vo.ShipmentRequest;
import com.shop.order.infrastructure.vo.ShipmentResponse;

public interface ShipmentService {

    ShipmentResponse createShipment(ShipmentRequest shipmentRequest);

    void updateShipmentStatus(ShipmentRequest shipmentRequest, Long shipmentId, String status);
}
