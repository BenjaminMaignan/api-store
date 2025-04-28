package com.bmaignan.apistore.cart.dto;

import com.bmaignan.apistore.cartitem.dto.CartItemRequestDto;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record CartRequestDto(
        UUID id,
        UUID customerId,
        List<CartItemRequestDto> cartItems) {
}
