package com.bmaignan.apistore.cart.mapper;

import com.bmaignan.apistore.cart.dto.CartRequestDto;
import com.bmaignan.apistore.cart.dto.CartResponseDto;
import com.bmaignan.apistore.cart.model.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart toEntity(CartRequestDto cartDTO);

    CartResponseDto toResponseDto(Cart cart);
}
