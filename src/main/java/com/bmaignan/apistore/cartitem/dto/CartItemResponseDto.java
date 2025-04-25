package com.bmaignan.apistore.cartitem.dto;

import com.bmaignan.apistore.articleitem.dto.ArticleItemResponseDTO;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CartItemResponseDto(
        UUID id,
        ArticleItemResponseDTO articleItem,
        Integer quantity
) {
}
