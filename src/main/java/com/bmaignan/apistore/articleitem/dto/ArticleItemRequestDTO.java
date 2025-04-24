package com.bmaignan.apistore.articleitem.dto;

import java.util.UUID;

public record ArticleItemRequestDTO(
        UUID id,
        UUID articleId,
        String size,
        String color,
        Integer availableStock
) {
}
