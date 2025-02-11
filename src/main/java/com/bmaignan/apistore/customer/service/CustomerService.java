package com.bmaignan.apistore.customer.service;

import com.bmaignan.apistore.customer.dto.CustomerRequestDto;
import com.bmaignan.apistore.customer.dto.CustomerResponseDto;

import java.util.UUID;

public interface CustomerService {
    CustomerResponseDto login(String email);

    CustomerResponseDto getCustomer(UUID id);

    CustomerResponseDto createCustomer(CustomerRequestDto customer);
}
