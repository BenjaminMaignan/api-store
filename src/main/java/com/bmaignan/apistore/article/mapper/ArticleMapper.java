package com.bmaignan.apistore.article.mapper;

import com.bmaignan.apistore.article.dto.ArticleRequestDTO;
import com.bmaignan.apistore.article.dto.ArticleLightResponseDTO;
import com.bmaignan.apistore.article.dto.ArticleResponseDTO;
import com.bmaignan.apistore.article.model.Article;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    Article toEntity(ArticleRequestDTO articleDTO);

    ArticleResponseDTO toResponseDTO(Article article);

    ArticleLightResponseDTO toLightResponseDTO(Article article);
}
