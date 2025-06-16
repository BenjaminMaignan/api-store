package com.bmaignan.apistore.customer.service;

import com.bmaignan.apistore.customer.dto.CustomerRequestDto;
import com.bmaignan.apistore.customer.dto.CustomerResponseDto;
import com.bmaignan.apistore.customer.mapper.CustomerMapper;
import com.bmaignan.apistore.customer.model.Customer;
import com.bmaignan.apistore.customer.repository.CustomerDao;
import com.bmaignan.apistore.customer.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerDao customerDao;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private UUID customerId;
    private Customer customer;
    private CustomerRequestDto customerRequestDto;
    private CustomerResponseDto customerResponseDto;

    @BeforeEach
    void setUp() {
        customerId = UUID.randomUUID();

        customer = Customer.builder()
                .id(customerId)
                .firstname("John")
                .lastname("Doe")
                .email("johndoe@test.xxx")
                .build();

        customerRequestDto = CustomerRequestDto.builder()
                .id(customerId)
                .firstname("John")
                .lastname("Doe")
                .email("johndoe@test.xxx")
                .build();

        customerResponseDto = CustomerResponseDto.builder()
                .id(customerId)
                .firstname("John")
                .lastname("Doe")
                .email("johndoe@test.xxx")
                .build();
    }

    @Test
    void findAllCustomers_shouldReturnAllCustomers() {
        // Given
        var customers = List.of(customer);

        // When
        when(customerDao.findAll()).thenReturn(customers);
        when(customerMapper.toResponseDto(customer)).thenReturn(customerResponseDto);

        // Then
        var result = customerService.findAllCustomers();

        assertEquals(1, result.size());
        assertEquals(customerResponseDto, result.getFirst());

        verify(customerDao).findAll();
    }

    @Test
    void getCustomer_shouldReturnCustomer() {
        // When
        when(customerDao.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerMapper.toResponseDto(customer)).thenReturn(customerResponseDto);

        // Then
        var result = customerService.getCustomer(customerId);

        assertEquals(customerResponseDto, result);
        verify(customerDao).findById(customerId);
        verify(customerMapper).toResponseDto(customer);
    }

    @Test
    void getCustomer_shouldThrowNotFound() {
        // Given
        var unknownId = UUID.randomUUID();

        // When
        when(customerDao.findById(unknownId)).thenReturn(Optional.empty());

        // Then
        assertThrows(RuntimeException.class, () -> customerService.getCustomer(unknownId));
    }

    @Test
    void createCustomer_shouldReturnCreatedCustomer() {
        // When
        when(customerMapper.toEntity(customerRequestDto)).thenReturn(customer);
        when(customerDao.save(customer)).thenReturn(customer);
        when(customerMapper.toResponseDto(customer)).thenReturn(customerResponseDto);

        // Then
        var result = customerService.createCustomer(customerRequestDto);

        assertEquals(customerResponseDto, result);

        verify(customerMapper).toEntity(customerRequestDto);
        verify(customerDao).save(customer);
        verify(customerMapper).toResponseDto(customer);
    }

    @Test
    void updateCustomer_shouldReturnUpdatedCustomer() {
        // When
        when(customerMapper.toEntity(customerRequestDto)).thenReturn(customer);
        when(customerDao.save(customer)).thenReturn(customer);
        when(customerMapper.toResponseDto(customer)).thenReturn(customerResponseDto);

        // Then
        var result = customerService.updateCustomer(customerId, customerRequestDto);

        assertEquals(customerResponseDto, result);

        verify(customerMapper).toEntity(customerRequestDto);
        verify(customerDao).save(customer);
        verify(customerMapper).toResponseDto(customer);
    }

    @Test
    void updateCustomer_shouldThrowNotFound() {
        // Given
        var unknownId = UUID.randomUUID();

        // When

        // Then
        assertThrows(RuntimeException.class, () -> customerService.updateCustomer(unknownId, customerRequestDto));
    }
}
