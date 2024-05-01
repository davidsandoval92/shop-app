package com.shop.customer.application.api;

import com.shop.customer.infrastructure.vo.CustomerRequest;
import com.shop.customer.infrastructure.vo.CustomerResponse;

public interface CustomerService {
    CustomerResponse findCustomerById(Long customerId);

    void createCustomer(CustomerRequest customerRequest);

    void deleteCustomerById(Long customerId);
}
