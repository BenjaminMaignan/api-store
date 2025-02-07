package com.bmaignan.apistore.article.service.impl;

import com.bmaignan.apistore.article.dto.ArticleRequestDTO;
import com.bmaignan.apistore.article.dto.ArticleResponseDTO;
import com.bmaignan.apistore.article.mapper.ArticleMapper;
import com.bmaignan.apistore.article.repository.ArticleDao;
import com.bmaignan.apistore.article.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.bmaignan.apistore.core.exception.ExceptionFactory.notFound;

@Service
@Transactional(readOnly = true)
public class ArticleServiceImpl implements ArticleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);

    private final ArticleDao articleDao;

    public ArticleServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public List<ArticleResponseDTO> findAllArticles() {
        return articleDao.findAll().stream()
                .map(ArticleMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ArticleResponseDTO getArticle(UUID id) {
        return
            articleDao.findById(id)
                .map(ArticleMapper::toResponseDTO)
                .orElseThrow(() -> notFound("Article not found"));
    }

    @Override
    @Transactional
    public ArticleResponseDTO createArticle(ArticleRequestDTO articleDTO) {
        return ArticleMapper.toResponseDTO(
                articleDao.save(ArticleMapper.toEntity(articleDTO))
        );
    }

    @Override
    @Transactional
    public ArticleResponseDTO updateArticle(UUID id, ArticleRequestDTO articleDTO) {
        if (!articleDTO.id().equals(id)) {
            throw notFound("Article not found");
        }

        return ArticleMapper.toResponseDTO(
                articleDao.save(ArticleMapper.toEntity(articleDTO))
        );
    }

    @Override
    @Transactional
    public void deleteArticle(UUID id) {
        articleDao.deleteById(id);
    }
}
