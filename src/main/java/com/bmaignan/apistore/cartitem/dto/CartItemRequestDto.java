package com.bmaignan.apistore.cartitem.dto;

import java.util.UUID;

public record CartItemRequestDto(
        UUID id,
        UUID cartId,
        UUID articleId,
        Integer quantity
) {
}
