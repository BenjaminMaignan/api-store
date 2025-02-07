package com.bmaignan.apistore.article.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ArticleResponseDTO(
    UUID id,
    String name,
    Float price
) {
}
