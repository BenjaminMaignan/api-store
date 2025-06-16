package com.bmaignan.apistore.cartitem.dto;

import com.bmaignan.apistore.articleitem.dto.ArticleCartItemResponseDTO;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record CartItemResponseDto(
        UUID id,
        ArticleCartItemResponseDTO articleItem,
        Integer quantity,
        Instant createdAt
) {
}
