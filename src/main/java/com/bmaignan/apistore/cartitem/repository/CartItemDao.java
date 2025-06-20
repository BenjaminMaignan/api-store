package com.bmaignan.apistore.cartitem.repository;

import com.bmaignan.apistore.cartitem.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartItemDao extends JpaRepository<CartItem, UUID> {
    Optional<CartItem> findByCartIdAndArticleItemId(UUID cartId, UUID articleItemId);
}
