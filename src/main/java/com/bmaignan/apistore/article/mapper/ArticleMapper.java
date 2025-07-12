package com.bmaignan.apistore.article.mapper;

import com.bmaignan.apistore.article.dto.ArticleRequestDTO;
import com.bmaignan.apistore.article.dto.ArticleLightResponseDTO;
import com.bmaignan.apistore.article.dto.ArticleResponseDTO;
import com.bmaignan.apistore.article.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    Article toEntity(ArticleRequestDTO articleDTO);

    ArticleResponseDTO toResponseDTO(Article article);

    @Mapping(target = "imageUrl", expression = "java(getFirstImageUrl(article))")
    ArticleLightResponseDTO toLightResponseDTO(Article article);

    default String getFirstImageUrl(Article article) {
        if (article.getArticleItems() != null && !article.getArticleItems().isEmpty()) {
            return article.getArticleItems().getFirst().getImageUrl();
        }
        return null;
    }
}
