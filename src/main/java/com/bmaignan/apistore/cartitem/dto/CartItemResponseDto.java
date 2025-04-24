package com.bmaignan.apistore.cartitem.dto;

import com.bmaignan.apistore.article.dto.ArticleLightResponseDTO;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CartItemResponseDto(
        UUID id,
        ArticleLightResponseDTO article,
        Integer quantity
) {
}
