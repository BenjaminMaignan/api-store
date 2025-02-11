package com.bmaignan.apistore.cart.repository;

import com.bmaignan.apistore.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartDao extends JpaRepository<Cart, UUID> {
}
