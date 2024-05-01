package com.shop.customer.domain.repository;

import com.shop.customer.domain.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
  Optional<CustomerEntity> findByCustomerId(Long customerId);
}
