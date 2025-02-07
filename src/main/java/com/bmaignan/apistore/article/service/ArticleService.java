package com.bmaignan.apistore.article.service;

import com.bmaignan.apistore.article.dto.ArticleRequestDTO;
import com.bmaignan.apistore.article.dto.ArticleResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ArticleService {
    List<ArticleResponseDTO> findAllArticles();

    ArticleResponseDTO getArticle(UUID id);

    ArticleResponseDTO createArticle(ArticleRequestDTO articleDTO);

    ArticleResponseDTO updateArticle(UUID id, ArticleRequestDTO articleDTO);

    void deleteArticle(UUID id);
}
