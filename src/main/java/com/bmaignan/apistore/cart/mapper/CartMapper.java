package com.bmaignan.apistore.cart.mapper;

import com.bmaignan.apistore.cart.dto.CartRequestDto;
import com.bmaignan.apistore.cart.dto.CartResponseDto;
import com.bmaignan.apistore.cart.model.Cart;

public class CartMapper {
    private CartMapper() {
    }

    public static Cart toEntity(CartRequestDto cartDTO) {

        return new Cart(
                cartDTO.id(),
                null,
                null);
    }

    public static CartResponseDto toResponseDto(Cart cart) {
        return new CartResponseDto(
                cart.getId(),
                null);
    }
}
