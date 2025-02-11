package com.bmaignan.apistore.cart.dto;

import java.util.UUID;

public record CartItemRequestDto(
        UUID id,
        UUID cartId,
        UUID articleId,
        Integer quantity
) {
}
