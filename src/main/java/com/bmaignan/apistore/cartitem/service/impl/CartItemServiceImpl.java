package com.bmaignan.apistore.cartitem.service.impl;

import com.bmaignan.apistore.cartitem.dto.CartItemRequestDto;
import com.bmaignan.apistore.cartitem.dto.CartItemResponseDto;
import com.bmaignan.apistore.cartitem.mapper.CartItemMapper;
import com.bmaignan.apistore.cartitem.repository.CartItemDao;
import com.bmaignan.apistore.cartitem.service.CartItemService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.bmaignan.apistore.core.exception.ExceptionFactory.notFound;

@Service
@Transactional(readOnly = true)
public class CartItemServiceImpl implements CartItemService {
    private final CartItemDao cartItemDao;
    private final CartItemMapper cartItemMapper;

    public CartItemServiceImpl(CartItemDao cartItemDao, CartItemMapper cartItemMapper) {
        this.cartItemDao = cartItemDao;
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public CartItemResponseDto getCartItem(UUID id) {
        return cartItemDao
                .findById(id)
                .map(cartItemMapper::toResponseDto)
                .orElseThrow(() -> notFound("Cart item not found"));
    }

    @Override
    @Transactional
    public CartItemResponseDto createCartItem(CartItemRequestDto cartItemDTO) {
        return cartItemMapper.toResponseDto(
                cartItemDao.save(cartItemMapper.toEntity(cartItemDTO))
        );
    }

    @Override
    @Transactional
    public CartItemResponseDto updateCartItem(UUID id, CartItemRequestDto cartItemDTO) {
        if (!cartItemDTO.id().equals(id)) {
            throw notFound("Cart item not found");
        }
        return cartItemMapper.toResponseDto(
                cartItemDao.save(cartItemMapper.toEntity(cartItemDTO))
        );
    }

    @Override
    @Transactional
    public void deleteCartItem(UUID id) {
        cartItemDao.deleteById(id);
    }

}
