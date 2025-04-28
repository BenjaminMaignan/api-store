package com.bmaignan.apistore.articleitem.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ArticleItemRequestDTO(
        UUID id,
        UUID articleId,
        String size,
        String color,
        Integer availableStock
) {
}
