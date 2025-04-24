package com.bmaignan.apistore.article.service;

import com.bmaignan.apistore.article.dto.ArticleLightResponseDTO;
import com.bmaignan.apistore.article.dto.ArticleRequestDTO;
import com.bmaignan.apistore.article.model.Article;

import java.util.List;
import java.util.UUID;

public interface ArticleService {
    List<ArticleLightResponseDTO> findAllArticles();

    /* Using by mapstruct */
    Article getEntity(UUID id);

    ArticleLightResponseDTO getArticle(UUID id);

    ArticleLightResponseDTO createArticle(ArticleRequestDTO articleDTO);

    ArticleLightResponseDTO updateArticle(UUID id, ArticleRequestDTO articleDTO);

    void deleteArticle(UUID id);
}
