package com.bmaignan.apistore.cartitem.controller;

import com.bmaignan.apistore.cartitem.dto.CartItemRequestDto;
import com.bmaignan.apistore.cartitem.dto.CartItemResponseDto;
import com.bmaignan.apistore.cartitem.service.CartItemService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping("/{cartItemId}")
    public CartItemResponseDto findAllCartItems(@PathVariable UUID cartItemId) {
        return cartItemService.getCartItem(cartItemId);
    }

    @PostMapping
    public CartItemResponseDto createCartItem(@RequestBody CartItemRequestDto cartItem) {
        return cartItemService.createCartItem(cartItem);
    }

    @PutMapping("/{cartItemId}")
    public CartItemResponseDto updateCartItem(@PathVariable UUID cartItemId, @RequestBody CartItemRequestDto cartItem) {
        return cartItemService.updateCartItem(cartItemId, cartItem);
    }

    @DeleteMapping("/{cartItemId}")
    public void deleteCartItem(@PathVariable UUID cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
    }
}
