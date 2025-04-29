package com.bmaignan.apistore.customer.dto;

import com.bmaignan.apistore.cart.dto.CartRequestDto;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CustomerRequestDto(
        UUID id,
        String firstname,
        String lastname,
        String email,
        CartRequestDto cart) {
}
