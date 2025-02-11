package com.bmaignan.apistore.customer.service;

import com.bmaignan.apistore.customer.dto.CustomerRequestDto;
import com.bmaignan.apistore.customer.dto.CustomerResponseDto;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<CustomerResponseDto> findAllCustomers();

    CustomerResponseDto getCustomer(UUID id);

    CustomerResponseDto createCustomer(CustomerRequestDto customerDTO);

    CustomerResponseDto updateCustomer(UUID id, CustomerRequestDto customerDTO);

    void deleteCustomer(UUID id);
}
