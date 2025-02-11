package com.bmaignan.apistore.article.mapper;

import com.bmaignan.apistore.article.dto.ArticleRequestDTO;
import com.bmaignan.apistore.article.dto.ArticleResponseDTO;
import com.bmaignan.apistore.article.model.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {
    private ArticleMapper() {
    }

    public static Article toEntity(ArticleRequestDTO articleDTO) {
        return new Article(
                articleDTO.id(),
                articleDTO.name(),
                articleDTO.price(),
                articleDTO.availableStock());
    }

    public static ArticleResponseDTO toResponseDTO(Article article) {
        return new ArticleResponseDTO(
                article.getId(),
                article.getName(),
                article.getPrice(),
                article.getAvailableStock());
    }
}
