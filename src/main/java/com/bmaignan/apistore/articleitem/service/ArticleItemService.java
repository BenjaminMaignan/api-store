package com.bmaignan.apistore.articleitem.service;

import com.bmaignan.apistore.articleitem.dto.ArticleItemRequestDTO;
import com.bmaignan.apistore.articleitem.dto.ArticleItemResponseDTO;
import com.bmaignan.apistore.articleitem.model.ArticleItem;

import java.util.UUID;

public interface ArticleItemService {
    ArticleItemResponseDTO getArticleItemById(UUID id);

    ArticleItem getEntity(UUID id);

    ArticleItemResponseDTO createArticleItem(ArticleItemRequestDTO articleItemRequestDTO);

    ArticleItemResponseDTO updateArticleItem(ArticleItemRequestDTO articleItemRequestDTO);

    void deleteArticleItem(UUID id);
}
