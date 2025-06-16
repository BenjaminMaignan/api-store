package com.bmaignan.apistore.articleitem.mapper;

import com.bmaignan.apistore.article.service.ArticleService;
import com.bmaignan.apistore.articleitem.dto.ArticleCartItemResponseDTO;
import com.bmaignan.apistore.articleitem.dto.ArticleItemRequestDTO;
import com.bmaignan.apistore.articleitem.dto.ArticleItemResponseDTO;
import com.bmaignan.apistore.articleitem.model.ArticleItem;
import com.bmaignan.apistore.articleitem.service.ArticleItemService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ArticleService.class, ArticleItemService.class})
public interface ArticleItemMapper {
    @Mapping(target = "article", source = "articleId")
    ArticleItem toEntity(ArticleItemRequestDTO articleItemDTO);

    ArticleItemResponseDTO toResponseDTO(ArticleItem articleItem);

    @Mapping(target = "articleId", source = "articleItem.article.id")
    @Mapping(target = "name", source = "articleItem.article.name")
    @Mapping(target = "price", source = "articleItem.article.price")
    ArticleCartItemResponseDTO toCartResponseDTO(ArticleItem articleItem);
}
