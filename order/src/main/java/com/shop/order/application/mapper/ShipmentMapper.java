package com.shop.order.application.mapper;

import com.shop.order.domain.entity.ShipmentEntity;
import com.shop.order.infrastructure.vo.ShipmentRequest;
import com.shop.order.infrastructure.vo.ShipmentResponse;

import java.time.LocalDateTime;
import java.util.Objects;

public class ShipmentMapper {

    public static ShipmentResponse entityToResponse(ShipmentEntity entity) {
        return new ShipmentResponse(entity.getShipmentId(), entity.getOrderId(), entity.getStatus(), entity.getCreated(), entity.getUpdated());
    }

    public static ShipmentEntity requestToEntity(ShipmentRequest rq) {
        return ShipmentEntity.builder()
                .orderId(rq.orderId())
                .status(rq.status())
                .created(Objects.nonNull(rq.created()) ? rq.created() : LocalDateTime.now())
                .updated(Objects.nonNull(rq.updated()) ? rq.updated() : LocalDateTime.now())
                .build();
    }


    public static ShipmentEntity updateStatusToEntity(ShipmentRequest rq, Long shipmentId, String status) {
        return ShipmentEntity.builder()
                .shipmentId(shipmentId)
                .orderId(rq.orderId())
                .status(status)
                .created(Objects.nonNull(rq.created()) ? rq.created() : LocalDateTime.now())
                .updated(LocalDateTime.now())
                .build();
    }

    public static ShipmentRequest responseToRequest(ShipmentResponse rs) {
        return new ShipmentRequest(rs.orderId(),
                rs.status(),
                rs.created(),
                rs.updated());
    }
}
