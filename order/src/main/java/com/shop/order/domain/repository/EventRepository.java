package com.shop.order.domain.repository;

import com.shop.order.domain.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    Page<EventEntity> findAllByOrderId(Long customerId, Pageable pageable);
}
