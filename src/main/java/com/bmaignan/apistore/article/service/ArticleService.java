package com.bmaignan.apistore.article.service;

import com.bmaignan.apistore.article.dto.ArticleRequestDTO;
import com.bmaignan.apistore.article.dto.ArticleResponseDTO;
import com.bmaignan.apistore.article.model.Article;

import java.util.List;
import java.util.UUID;

public interface ArticleService {
    List<ArticleResponseDTO> findAllArticles();

    /* Using by mapstruct */
    Article getEntity(UUID id);

    ArticleResponseDTO getArticle(UUID id);

    ArticleResponseDTO createArticle(ArticleRequestDTO articleDTO);

    ArticleResponseDTO updateArticle(UUID id, ArticleRequestDTO articleDTO);

    void deleteArticle(UUID id);
}
