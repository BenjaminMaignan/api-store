package com.bmaignan.apistore.article.service.impl;

import com.bmaignan.apistore.article.dto.ArticleLightResponseDTO;
import com.bmaignan.apistore.article.dto.ArticleRequestDTO;
import com.bmaignan.apistore.article.mapper.ArticleMapper;
import com.bmaignan.apistore.article.model.Article;
import com.bmaignan.apistore.article.repository.ArticleDao;
import com.bmaignan.apistore.article.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.bmaignan.apistore.core.exception.ExceptionFactory.conflict;
import static com.bmaignan.apistore.core.exception.ExceptionFactory.notFound;

@Service
@Transactional(readOnly = true)
public class ArticleServiceImpl implements ArticleService {
    private final ArticleDao articleDao;
    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleDao articleDao, ArticleMapper articleMapper) {
        this.articleDao = articleDao;
        this.articleMapper = articleMapper;
    }

    @Override
    public List<ArticleLightResponseDTO> findAllArticles() {
        return articleDao.findAll().stream()
                .map(articleMapper::toResponseDTO)
                .toList();
    }

    @Override
    public Article getEntity(UUID id) {
        return articleDao.findById(id)
                .orElseThrow(() -> notFound("Article not found"));
    }

    @Override
    public ArticleLightResponseDTO getArticle(UUID id) {
        return
                articleDao.findById(id)
                        .map(articleMapper::toResponseDTO)
                        .orElseThrow(() -> notFound("Article not found"));
    }

    @Override
    @Transactional
    public ArticleLightResponseDTO createArticle(ArticleRequestDTO articleDTO) {
        return articleMapper.toResponseDTO(
                articleDao.save(articleMapper.toEntity(articleDTO))
        );
    }

    @Override
    @Transactional
    public ArticleLightResponseDTO updateArticle(UUID id, ArticleRequestDTO articleDTO) {
        if (!articleDTO.id().equals(id)) {
            throw conflict("Article");
        }

        return articleMapper.toResponseDTO(
                articleDao.save(articleMapper.toEntity(articleDTO))
        );
    }

    @Override
    @Transactional
    public void deleteArticle(UUID id) {
        articleDao.deleteById(id);
    }
}
