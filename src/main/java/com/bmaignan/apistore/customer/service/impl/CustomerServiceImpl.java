package com.bmaignan.apistore.customer.service.impl;

import com.bmaignan.apistore.customer.dto.CustomerRequestDto;
import com.bmaignan.apistore.customer.dto.CustomerResponseDto;
import com.bmaignan.apistore.customer.mapper.CustomerMapper;
import com.bmaignan.apistore.customer.repository.CustomerDao;
import com.bmaignan.apistore.customer.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.bmaignan.apistore.core.exception.ExceptionFactory.notFound;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public CustomerResponseDto login(String email) {
        return customerDao.findByEmail(email)
                .map(CustomerMapper::toResponseDto)
                .orElseThrow(() -> notFound("Customer not found"));
    }

    @Override
    public CustomerResponseDto getCustomer(UUID id) {
        return customerDao.findById(id)
                .map(CustomerMapper::toResponseDto)
                .orElseThrow(() -> notFound("Customer not found"));
    }

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto customer) {
        return CustomerMapper.toResponseDto(customerDao.save(CustomerMapper.toEntity(customer)));
    }
}
