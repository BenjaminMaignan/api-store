package com.bmaignan.apistore.cart.dto;

import java.util.List;
import java.util.UUID;

public record CartRequestDto(
        UUID id,
        UUID customerId,
        List<CartItemRequestDto> cartItems) {
}
