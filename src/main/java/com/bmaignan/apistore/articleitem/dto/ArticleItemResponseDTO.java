package com.bmaignan.apistore.articleitem.dto;

public record ArticleItemResponseDTO(
        String id,
        String size,
        String color,
        Integer availableStock
) {
}
