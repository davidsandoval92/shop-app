package com.shop.order.application.api;

import com.shop.order.domain.entity.ShipmentEntity;
import com.shop.order.infrastructure.vo.ShipmentRequest;
import com.shop.order.infrastructure.vo.ShipmentResponse;

public interface ShipmentService {

    ShipmentResponse createShipment(ShipmentEntity shipmentEntity);

    void updateShipmentStatus(ShipmentEntity shipmentEntity);
}
