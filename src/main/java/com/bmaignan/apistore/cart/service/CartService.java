package com.bmaignan.apistore.cart.service;

import com.bmaignan.apistore.cart.dto.CartRequestDto;
import com.bmaignan.apistore.cart.dto.CartResponseDto;

import java.util.List;
import java.util.UUID;

public interface CartService {
    List<CartResponseDto> findAllCarts();

    CartResponseDto getCart(UUID id);

    CartResponseDto createCart(CartRequestDto cartDTO);

    CartResponseDto updateCart(UUID id, CartRequestDto cartDTO);

    void deleteCart(UUID id);
}
