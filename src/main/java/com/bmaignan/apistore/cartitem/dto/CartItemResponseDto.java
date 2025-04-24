package com.bmaignan.apistore.cartitem.dto;

import com.bmaignan.apistore.article.dto.ArticleResponseLightDTO;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CartItemResponseDto(
        UUID id,
        ArticleResponseLightDTO article,
        Integer quantity
) {
}
