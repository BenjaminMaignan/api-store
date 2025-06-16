package com.bmaignan.apistore.cart.service;

import com.bmaignan.apistore.cart.dto.CartRequestDto;
import com.bmaignan.apistore.cart.dto.CartResponseDto;
import com.bmaignan.apistore.cart.mapper.CartMapper;
import com.bmaignan.apistore.cart.model.Cart;
import com.bmaignan.apistore.cart.repository.CartDao;
import com.bmaignan.apistore.cart.service.impl.CartServiceImpl;
import com.bmaignan.apistore.cartitem.dto.CartItemRequestDto;
import com.bmaignan.apistore.cartitem.dto.CartItemResponseDto;
import com.bmaignan.apistore.cartitem.model.CartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartDao cartDao;

    @Mock
    private CartMapper cartMapper;

    @InjectMocks
    private CartServiceImpl cartService;

    private UUID cartId;
    private Cart cart;

    @BeforeEach
    void setUp() {
        cartId = UUID.randomUUID();

        cart = Cart.builder()
                .id(cartId)
                .createdAt(Instant.now())
                .build();

        var cartItem = CartItem.builder()
                .id(UUID.randomUUID())
                .cart(cart)
                .quantity(1)
                .build();

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        cart.setCartItems(cartItems);
    }

    @Test
    void findAllCarts_shouldReturnAllCarts() {
        // Given
        List<Cart> carts = List.of(cart);

        var cartResponseDto = CartResponseDto.builder()
                .id(cartId)
                .cartItems(cart.getCartItems().stream()
                        .map(c -> new CartItemResponseDto(c.getId(), null, c.getQuantity(), null))
                        .toList())
                .build();

        // When
        when(cartDao.findAll()).thenReturn(carts);
        when(cartMapper.toResponseDto(cart)).thenReturn(cartResponseDto);

        // Then
        var result = cartService.findAllCarts();

        assertEquals(1, result.size());
        assertEquals(cartId, result.getFirst().id());
        assertEquals(1, result.getFirst().cartItems().size());
        verify(cartDao).findAll();
    }

    @Test
    void getCart_shouldReturnCart() {
        // Given
        var cartResponseDto = new CartResponseDto(
                cartId,
                cart.getCartItems().stream()
                        .map(c -> new CartItemResponseDto(c.getId(), null, c.getQuantity(), null))
                        .toList()
        );

        // When
        when(cartDao.findById(cartId)).thenReturn(Optional.of(cart));
        when(cartMapper.toResponseDto(cart)).thenReturn(cartResponseDto);

        // Then
        var result = cartService.getCart(cartId);

        assertEquals(cartId, result.id());
        assertEquals(1, result.cartItems().size());
        verify(cartDao).findById(cartId);
    }

    @Test
    void getCart_shouldThrowNotFound() {
        // Given
        var unknownId = UUID.randomUUID();

        // When
        when(cartDao.findById(unknownId)).thenReturn(Optional.empty());

        // Then
        assertThrows(RuntimeException.class, () -> cartService.getCart(unknownId));
        verify(cartDao).findById(unknownId);
    }

    @Test
    void createCart_shouldReturnCreatedCart() {
        // Given
        var cartRequestDto = CartRequestDto.builder().build();

        var cartResponseDto = CartResponseDto.builder()
                .id(cartId)
                .cartItems(List.of(new CartItemResponseDto(cart.getCartItems().getFirst().getId(), null, 1, null)))
                .build();

        // When
        when(cartMapper.toEntity(cartRequestDto)).thenReturn(cart);
        when(cartDao.save(cart)).thenReturn(cart);
        when(cartMapper.toResponseDto(cart)).thenReturn(cartResponseDto);

        // Then
        var result = cartService.createCart(cartRequestDto);

        assertEquals(cartId, result.id());

        verify(cartMapper).toEntity(cartRequestDto);
        verify(cartDao).save(cart);
        verify(cartMapper).toResponseDto(cart);
    }

    @Test
    void updateCart_shouldReturnUpdatedCart() {
        // Given
        var cartRequestDto = CartRequestDto.builder()
                .id(cartId)
                .cartItems(List.of(new CartItemRequestDto(cart.getCartItems().getFirst().getId(), null, null, 1)))
                .build();

        var cartResponseDto = CartResponseDto.builder()
                .id(cartId)
                .cartItems(List.of(new CartItemResponseDto(cart.getCartItems().getFirst().getId(), null, 1, null)))
                .build();

        // When
        when(cartMapper.toEntity(cartRequestDto)).thenReturn(cart);
        when(cartDao.save(cart)).thenReturn(cart);
        when(cartMapper.toResponseDto(cart)).thenReturn(cartResponseDto);

        // Then
        var result = cartService.updateCart(cartId, cartRequestDto);

        assertEquals(cartId, result.id());

        verify(cartMapper).toEntity(cartRequestDto);
        verify(cartDao).save(cart);
        verify(cartMapper).toResponseDto(cart);
    }

    @Test
    void updateCart_shouldThrowConflict() {
        // Given
        var cartRequestDto = CartRequestDto.builder()
                .id(UUID.randomUUID())
                .cartItems(List.of(new CartItemRequestDto(cart.getCartItems().getFirst().getId(), null, null, 1)))
                .build();

        // When
        assertThrows(RuntimeException.class, () -> cartService.updateCart(cartId, cartRequestDto));
    }

    @Test
    void deleteCart_shouldDeleteCart() {
        // Given

        // When
        cartService.deleteCart(cartId);

        // Then
        verify(cartDao).deleteById(cartId);
    }
}
