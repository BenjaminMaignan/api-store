package com.bmaignan.apistore.cart.model;

import com.bmaignan.apistore.article.model.Article;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Article article;

    private Integer quantity;
}
