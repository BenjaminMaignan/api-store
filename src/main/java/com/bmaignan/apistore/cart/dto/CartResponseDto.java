package com.bmaignan.apistore.cart.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record CartResponseDto(
    UUID id,
    List<CartItemResponseDto> cartItems
) {
}
