package com.shop.order.application.mapper;

import com.shop.order.domain.entity.ShipmentEntity;
import com.shop.order.infrastructure.vo.ShipmentResponse;

public class ShipmentMapper {

    public static ShipmentResponse entityToResponse(ShipmentEntity entity) {
        return new ShipmentResponse(entity.getShipmentId(), entity.getOrder().getOrderId(), entity.getStatus(), entity.getCreated(), entity.getUpdated());
    }
}
