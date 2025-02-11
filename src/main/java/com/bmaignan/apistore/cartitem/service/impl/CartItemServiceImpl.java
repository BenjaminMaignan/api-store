package com.bmaignan.apistore.cartitem.service.impl;

import com.bmaignan.apistore.cartitem.dto.CartItemRequestDto;
import com.bmaignan.apistore.cartitem.dto.CartItemResponseDto;
import com.bmaignan.apistore.cartitem.mapper.CartItemMapper;
import com.bmaignan.apistore.cartitem.repository.CartItemDao;
import com.bmaignan.apistore.cartitem.service.CartItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.logging.Logger;

import static com.bmaignan.apistore.core.exception.ExceptionFactory.notFound;

@Service
@Transactional(readOnly = true)
public class CartItemServiceImpl implements CartItemService {
    private static final Logger LOGGER = Logger.getLogger(CartItemServiceImpl.class.getName());

    private final CartItemDao cartItemDao;

    public CartItemServiceImpl(CartItemDao cartItemDao) {
        this.cartItemDao = cartItemDao;
    }

    @Override
    public CartItemResponseDto getCartItem(UUID id) {
        return cartItemDao
                .findById(id)
                .map(CartItemMapper::toResponseDto)
                .orElseThrow(() -> notFound("Cart item not found"));
    }

    @Override
    public CartItemResponseDto createCartItem(CartItemRequestDto cartItemDTO) {
        return CartItemMapper.toResponseDto(
                cartItemDao.save(CartItemMapper.toEntity(cartItemDTO))
        );
    }

    @Override
    public CartItemResponseDto updateCartItem(UUID id, CartItemRequestDto cartItemDTO) {
        if (!cartItemDTO.id().equals(id)) {
            throw notFound("Cart item not found");
        }
        return CartItemMapper.toResponseDto(cartItemDao.save(CartItemMapper.toEntity(cartItemDTO)));
    }

    @Override
    public void deleteCartItem(UUID id) {
        cartItemDao.deleteById(id);
    }

}
