package com.bmaignan.apistore.cartitem.service;

import com.bmaignan.apistore.cartitem.dto.CartItemRequestDto;
import com.bmaignan.apistore.cartitem.dto.CartItemResponseDto;

import java.util.UUID;

public interface CartItemService {
    CartItemResponseDto getCartItem(UUID cartItemId);

    CartItemResponseDto createCartItem(CartItemRequestDto cartItemDTO);

    CartItemResponseDto updateCartItem(UUID id, CartItemRequestDto cartItemDTO);

    void deleteCartItem(UUID id);
}
