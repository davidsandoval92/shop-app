package com.shop.customer.application.impl;

import com.shop.customer.application.api.CustomerService;
import com.shop.customer.application.mapper.CustomerMapper;
import com.shop.customer.domain.entity.CustomerEntity;
import com.shop.customer.domain.exception.CustomerException;
import com.shop.customer.domain.repository.CustomerRepository;
import com.shop.customer.infrastructure.vo.CustomerRequest;
import com.shop.customer.infrastructure.vo.CustomerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Autowired
  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public CustomerResponse findCustomerById(Long customerId) {
    // this line can be uncommented to test the CircuitBreaker
    // throw new RuntimeException();
    log.info("[CustomerServiceImpl] findCustomerById by customer id: " + customerId);
    return CustomerMapper.entityToResponse(
        customerRepository
            .findByCustomerId(customerId)
            .orElseThrow(
                () ->
                    new CustomerException(
                        "Customer not found by id " + customerId, HttpStatus.NOT_FOUND)));
  }

  @Override
  public void createCustomer(CustomerRequest customerRequest) {
    log.info("[CustomerServiceImpl] createCustomer by customer request: " + customerRequest);
    CustomerEntity customerEntity = CustomerMapper.requestToEntity(customerRequest);
    customerRepository.save(customerEntity);
  }

  @Override
  public void deleteCustomerById(Long customerId) {
    log.info("[CustomerServiceImpl] deleteCustomerById by customer id: " + customerId);
    customerRepository.deleteById(customerId);
  }
}
