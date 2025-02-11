package com.bmaignan.apistore.cart.service.impl;

import com.bmaignan.apistore.cart.dto.CartRequestDto;
import com.bmaignan.apistore.cart.dto.CartResponseDto;
import com.bmaignan.apistore.cart.mapper.CartMapper;
import com.bmaignan.apistore.cart.repository.CartDao;
import com.bmaignan.apistore.cart.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.bmaignan.apistore.core.exception.ExceptionFactory.notFound;

@Service
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    private final CartDao cartDao;

    public CartServiceImpl(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Override
    public List<CartResponseDto> findAllCarts() {
        return cartDao.findAll().stream()
                .map(CartMapper::toResponseDto)
                .toList();
    }

    @Override
    public CartResponseDto getCart(UUID id) {
        return cartDao.findById(id)
                .map(CartMapper::toResponseDto)
                .orElseThrow(() -> notFound("Cart not found"));
    }

    @Override
    @Transactional
    public CartResponseDto createCart(CartRequestDto cartDTO) {
        return CartMapper.toResponseDto(
                cartDao.save(CartMapper.toEntity(cartDTO))
        );
    }

    @Override
    @Transactional
    public CartResponseDto updateCart(UUID id, CartRequestDto cartDTO) {
        if (!cartDTO.id().equals(id)) {
            throw notFound("Cart not found"); // FIXME: change the error
        }

        return CartMapper.toResponseDto(
                cartDao.save(CartMapper.toEntity(cartDTO))
        );
    }

    @Override
    @Transactional
    public void deleteCart(UUID id) {
        cartDao.deleteById(id);
    }
}
