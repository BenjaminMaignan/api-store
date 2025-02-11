package com.bmaignan.apistore.cartitem.mapper;

import com.bmaignan.apistore.article.mapper.ArticleMapper;
import com.bmaignan.apistore.article.model.Article;
import com.bmaignan.apistore.cart.model.Cart;
import com.bmaignan.apistore.cartitem.dto.CartItemRequestDto;
import com.bmaignan.apistore.cartitem.dto.CartItemResponseDto;
import com.bmaignan.apistore.cartitem.model.CartItem;

public class CartItemMapper {
    private CartItemMapper() {
    }

    public static CartItem toEntity(CartItemRequestDto cartItemDTO) {
        var cart = Cart.builder()
                .id(cartItemDTO.cartId())
                .build();

        var article = Article.builder()
                .id(cartItemDTO.articleId())
                .build();

        return new CartItem(
                cartItemDTO.id(),
                cart,
                article,
                cartItemDTO.quantity());
    }

    public static CartItemResponseDto toResponseDto(CartItem cartItem) {
        var articleDTO = ArticleMapper.toResponseDTO(cartItem.getArticle());

        return new CartItemResponseDto(
                cartItem.getId(),
                articleDTO,
                cartItem.getQuantity());
    }
}
