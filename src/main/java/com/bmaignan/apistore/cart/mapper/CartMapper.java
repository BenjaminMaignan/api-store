package com.bmaignan.apistore.cart.mapper;

import com.bmaignan.apistore.cartitem.dto.CartItemResponseDto;
import com.bmaignan.apistore.cart.dto.CartRequestDto;
import com.bmaignan.apistore.cart.dto.CartResponseDto;
import com.bmaignan.apistore.cart.model.Cart;
import com.bmaignan.apistore.cartitem.mapper.CartItemMapper;

import java.util.List;

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
        List<CartItemResponseDto> cartItems = cart.getCartItems().stream()
                .map(CartItemMapper::toResponseDto)
                .toList();

        return new CartResponseDto(
                cart.getId(),
                cartItems);
    }
}
