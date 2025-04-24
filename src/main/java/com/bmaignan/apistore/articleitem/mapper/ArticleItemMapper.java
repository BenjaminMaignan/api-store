package com.bmaignan.apistore.articleitem.mapper;

import com.bmaignan.apistore.article.service.ArticleService;
import com.bmaignan.apistore.articleitem.dto.ArticleItemRequestDTO;
import com.bmaignan.apistore.articleitem.dto.ArticleItemResponseDTO;
import com.bmaignan.apistore.articleitem.model.ArticleItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ArticleService.class})
public interface ArticleItemMapper {
    @Mapping(target = "article", source = "articleId")
    ArticleItem toEntity(ArticleItemRequestDTO articleItemDTO);

    ArticleItemResponseDTO toResponseDTO(ArticleItem articleItem);
}
