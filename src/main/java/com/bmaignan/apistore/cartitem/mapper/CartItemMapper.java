package com.bmaignan.apistore.cartitem.mapper;

import com.bmaignan.apistore.article.service.ArticleService;
import com.bmaignan.apistore.cart.service.CartService;
import com.bmaignan.apistore.cartitem.dto.CartItemRequestDto;
import com.bmaignan.apistore.cartitem.dto.CartItemResponseDto;
import com.bmaignan.apistore.cartitem.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ArticleService.class, CartService.class})
public interface CartItemMapper {

    @Mapping(target = "article", source = "articleId")
    @Mapping(target = "cart", source = "cartId")
    CartItem toEntity(CartItemRequestDto cartItemDTO);

    CartItemResponseDto toResponseDto(CartItem cartItem);
}
