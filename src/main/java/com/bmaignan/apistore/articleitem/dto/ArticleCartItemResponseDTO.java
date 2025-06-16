package com.bmaignan.apistore.articleitem.dto;

import java.util.UUID;

public record ArticleCartItemResponseDTO(
        UUID id,
        UUID articleId,
        String name,
        Float price,
        String size,
        String color,
        Integer availableStock
) {
}
