package com.bmaignan.apistore.article.service;

import com.bmaignan.apistore.article.dto.ArticleRequestDTO;
import com.bmaignan.apistore.article.dto.ArticleResponseLightDTO;
import com.bmaignan.apistore.article.model.Article;

import java.util.List;
import java.util.UUID;

public interface ArticleService {
    List<ArticleResponseLightDTO> findAllArticles();

    /* Using by mapstruct */
    Article getEntity(UUID id);

    ArticleResponseLightDTO getArticle(UUID id);

    ArticleResponseLightDTO createArticle(ArticleRequestDTO articleDTO);

    ArticleResponseLightDTO updateArticle(UUID id, ArticleRequestDTO articleDTO);

    void deleteArticle(UUID id);
}
