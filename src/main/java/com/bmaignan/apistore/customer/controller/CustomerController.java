package com.bmaignan.apistore.customer.controller;

import com.bmaignan.apistore.customer.dto.CustomerRequestDto;
import com.bmaignan.apistore.customer.dto.CustomerResponseDto;
import com.bmaignan.apistore.customer.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/login")
    public CustomerResponseDto login(@RequestParam String email) {
        return customerService.login(email);
    }

    @GetMapping("/{id}")
    public CustomerResponseDto getCustomer(@PathVariable UUID id) {
        return customerService.getCustomer(id);
    }

    @PostMapping
    public CustomerResponseDto createCustomer(@RequestBody CustomerRequestDto customer) {
        return customerService.createCustomer(customer);
    }
}
