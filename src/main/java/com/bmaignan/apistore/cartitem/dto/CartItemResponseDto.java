package com.bmaignan.apistore.cartitem.dto;

import com.bmaignan.apistore.article.dto.ArticleResponseDTO;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CartItemResponseDto(
        UUID id,
        ArticleResponseDTO article,
        Integer quantity
) {
}
