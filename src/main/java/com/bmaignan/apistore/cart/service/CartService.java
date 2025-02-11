package com.bmaignan.apistore.cart.service;

import com.bmaignan.apistore.cart.dto.CartRequestDto;
import com.bmaignan.apistore.cart.dto.CartResponseDto;
import com.bmaignan.apistore.cart.model.Cart;

import java.util.List;
import java.util.UUID;

public interface CartService {
    List<CartResponseDto> findAllCarts();

    /* Using by mapstruct */
    Cart getEntity(UUID id);

    CartResponseDto getCart(UUID id);

    CartResponseDto createCart(CartRequestDto cartDTO);

    CartResponseDto updateCart(UUID id, CartRequestDto cartDTO);

    void deleteCart(UUID id);
}
