package com.bmaignan.apistore.article.mapper;

import com.bmaignan.apistore.article.dto.ArticleRequestDTO;
import com.bmaignan.apistore.article.dto.ArticleResponseLightDTO;
import com.bmaignan.apistore.article.model.Article;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    Article toEntity(ArticleRequestDTO articleDTO);

    ArticleResponseLightDTO toResponseDTO(Article article);
}
