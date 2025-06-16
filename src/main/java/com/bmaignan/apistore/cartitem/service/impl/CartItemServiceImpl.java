package com.bmaignan.apistore.cartitem.service.impl;

import com.bmaignan.apistore.cart.repository.CartDao;
import com.bmaignan.apistore.cartitem.dto.CartItemRequestDto;
import com.bmaignan.apistore.cartitem.dto.CartItemResponseDto;
import com.bmaignan.apistore.cartitem.mapper.CartItemMapper;
import com.bmaignan.apistore.cartitem.repository.CartItemDao;
import com.bmaignan.apistore.cartitem.service.CartItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.bmaignan.apistore.core.exception.ExceptionFactory.*;

@Service
@Transactional(readOnly = true)
public class CartItemServiceImpl implements CartItemService {
    private final CartItemDao cartItemDao;
    private final CartItemMapper cartItemMapper;
    private final CartDao cartDao;

    public CartItemServiceImpl(CartItemDao cartItemDao, CartItemMapper cartItemMapper, CartDao cartDao) {
        this.cartItemDao = cartItemDao;
        this.cartItemMapper = cartItemMapper;
        this.cartDao = cartDao;
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
        if (cartItemDao.findByCartIdAndArticleItemId(cartItemDTO.cartId(), cartItemDTO.articleItemId()).isPresent()) {
            throw alreadyExists("Cart item already exists");
        }

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
        var cartItem = cartItemDao.findById(id).orElse(null);
        if (cartItem != null) {
            var cart = cartItem.getCart();
            if (cart != null) {
                cart.getCartItems().remove(cartItem);
                cartDao.save(cart);
            }
            cartItemDao.deleteById(id);
        }
    }

}
