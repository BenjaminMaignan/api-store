package com.bmaignan.apistore.article.dto;

import com.bmaignan.apistore.articleitem.dto.ArticleItemRequestDTO;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record ArticleRequestDTO(
        UUID id,
        String name,
        Float price,
        List<ArticleItemRequestDTO> articleItems
) {
}
