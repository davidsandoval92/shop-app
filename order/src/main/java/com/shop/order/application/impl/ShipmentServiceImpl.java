package com.shop.order.application.impl;

import com.shop.order.application.api.ShipmentService;
import com.shop.order.application.mapper.ShipmentMapper;
import com.shop.order.domain.repository.ShipmentRepository;
import com.shop.order.infrastructure.vo.ShipmentRequest;
import com.shop.order.infrastructure.vo.ShipmentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository repository;

    @Autowired
    public ShipmentServiceImpl(ShipmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public ShipmentResponse createShipment(ShipmentRequest shipmentRequest) {
        return ShipmentMapper.entityToResponse(repository.save(ShipmentMapper.requestToEntity(shipmentRequest)));
    }

    @Override
    public void updateShipmentStatus(ShipmentRequest shipmentRequest, Long shipmentId, String status) {
        repository.save(ShipmentMapper.updateStatusToEntity(shipmentRequest, shipmentId, status));
    }

}
