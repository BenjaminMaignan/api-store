package com.bmaignan.apistore.cartitem.mapper;

import com.bmaignan.apistore.articleitem.service.ArticleItemService;
import com.bmaignan.apistore.cart.service.CartService;
import com.bmaignan.apistore.cartitem.dto.CartItemRequestDto;
import com.bmaignan.apistore.cartitem.dto.CartItemResponseDto;
import com.bmaignan.apistore.cartitem.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ArticleItemService.class, CartService.class})
public interface CartItemMapper {

    @Mapping(target = "articleItem", source = "articleItemId")
    @Mapping(target = "cart", source = "cartId")
    CartItem toEntity(CartItemRequestDto cartItemDTO);

    CartItemResponseDto toResponseDto(CartItem cartItem);
}
