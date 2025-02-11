package com.bmaignan.apistore.customer.dto;

import com.bmaignan.apistore.cart.dto.CartRequestDto;

import java.util.UUID;


public record CustomerRequestDto(
        UUID id,
        String firstname,
        String lastname,
        String email,
        CartRequestDto cart) {
}
