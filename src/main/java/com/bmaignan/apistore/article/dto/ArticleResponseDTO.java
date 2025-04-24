package com.bmaignan.apistore.article.dto;

import com.bmaignan.apistore.articleitem.dto.ArticleItemResponseDTO;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record ArticleResponseDTO(
        UUID id,
        String name,
        Float price,
        List<ArticleItemResponseDTO> articleItems
) {
}
