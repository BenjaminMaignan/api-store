package com.bmaignan.apistore.cart.service.impl;

import com.bmaignan.apistore.cart.dto.CartRequestDto;
import com.bmaignan.apistore.cart.dto.CartResponseDto;
import com.bmaignan.apistore.cart.mapper.CartMapper;
import com.bmaignan.apistore.cart.model.Cart;
import com.bmaignan.apistore.cart.repository.CartDao;
import com.bmaignan.apistore.cart.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.bmaignan.apistore.core.exception.ExceptionFactory.notFound;

@Service
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {
    private final CartDao cartDao;
    private final CartMapper cartMapper;

    public CartServiceImpl(CartDao cartDao, CartMapper cartMapper) {
        this.cartDao = cartDao;
        this.cartMapper = cartMapper;
    }

    @Override
    public List<CartResponseDto> findAllCarts() {
        return cartDao.findAll().stream()
                .map(cartMapper::toResponseDto)
                .toList();
    }

    @Override
    public Cart getEntity(UUID id) {
        return cartDao.findById(id)
                .orElseThrow(() -> notFound("Cart not found"));
    }

    @Override
    public CartResponseDto getCart(UUID id) {
        return cartDao.findById(id)
                .map(cartMapper::toResponseDto)
                .orElseThrow(() -> notFound("Cart not found"));
    }

    @Override
    @Transactional
    public CartResponseDto createCart(CartRequestDto cartDTO) {
        return cartMapper.toResponseDto(
                cartDao.save(cartMapper.toEntity(cartDTO))
        );
    }

    @Override
    @Transactional
    public CartResponseDto updateCart(UUID id, CartRequestDto cartDTO) {
        if (!cartDTO.id().equals(id)) {
            throw notFound("Cart not found"); // FIXME: change the error
        }

        return cartMapper.toResponseDto(
                cartDao.save(cartMapper.toEntity(cartDTO))
        );
    }

    @Override
    @Transactional
    public void deleteCart(UUID id) {
        cartDao.deleteById(id);
    }
}
