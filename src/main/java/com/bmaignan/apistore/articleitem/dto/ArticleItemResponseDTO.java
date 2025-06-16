package com.bmaignan.apistore.articleitem.dto;

import java.util.UUID;

public record ArticleItemResponseDTO(
        UUID id,
        String size,
        String color,
        Integer availableStock
) {
}
