package com.bmaignan.apistore.customer.mapper;

import com.bmaignan.apistore.customer.dto.CustomerRequestDto;
import com.bmaignan.apistore.customer.dto.CustomerResponseDto;
import com.bmaignan.apistore.customer.model.Customer;

public class CustomerMapper {
    private CustomerMapper() {
    }

    public static Customer toEntity(CustomerRequestDto customerDTO) {
        return new Customer(
                customerDTO.id(),
                customerDTO.firstName(),
                customerDTO.lastName(),
                customerDTO.email(),
                null);
    }

    public static CustomerResponseDto toResponseDto(Customer customer) {
        return new CustomerResponseDto(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                null);
    }
}
