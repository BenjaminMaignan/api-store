package com.bmaignan.apistore.cart.controller;

import com.bmaignan.apistore.cart.dto.CartRequestDto;
import com.bmaignan.apistore.cart.dto.CartResponseDto;
import com.bmaignan.apistore.cart.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<CartResponseDto> findAllCarts() {
        return cartService.findAllCarts();
    }

    @GetMapping("/{id}")
    public CartResponseDto getCart(@PathVariable UUID id) {
        return cartService.getCart(id);
    }

    @PostMapping
    public CartResponseDto createCart(@RequestBody CartRequestDto cart) {
        return cartService.createCart(cart);
    }

    @PutMapping("/{id}")
    public CartResponseDto updateCart(@PathVariable UUID id, @RequestBody CartRequestDto cart) {
        return cartService.updateCart(id, cart);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable UUID id) {
        cartService.deleteCart(id);
    }
}
