package com.bmaignan.apistore.article.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ArticleRequestDTO(
        UUID id,
        String name,
        Float price,
        Integer availableStock
) {
}
