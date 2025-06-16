package com.bmaignan.apistore.article.service.impl;

import com.bmaignan.apistore.article.criteria.ArticleCriteria;
import com.bmaignan.apistore.article.dto.ArticleLightResponseDTO;
import com.bmaignan.apistore.article.dto.ArticleRequestDTO;
import com.bmaignan.apistore.article.dto.ArticleResponseDTO;
import com.bmaignan.apistore.article.mapper.ArticleMapper;
import com.bmaignan.apistore.article.model.Article;
import com.bmaignan.apistore.article.repository.ArticleDao;
import com.bmaignan.apistore.article.service.ArticleService;
import com.bmaignan.apistore.core.specification.Operation;
import com.bmaignan.apistore.core.specification.SpecificationBuilder;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
    public List<ArticleLightResponseDTO> findAllArticles(ArticleCriteria criteria) {
        if (criteria == null) {
            criteria = new ArticleCriteria();
        }

        Specification<Article> spec = new SpecificationBuilder<Article>()
                .with("name", criteria.getName(), Operation.LIKE)
                .build();

        String sortValue = criteria.getSort();

        sortValue = sortValue == null ? "" : sortValue.toLowerCase();

        Sort sort = switch (sortValue) {
            case "price_desc" -> Sort.by(Sort.Direction.DESC, "price");
            case "price_asc" -> Sort.by(Sort.Direction.ASC, "price");
            default -> Sort.unsorted();
        };

        return articleDao.findAll(spec, sort).stream()
                .map(articleMapper::toLightResponseDTO)
                .toList();
    }

    @Override
    public Article getEntity(UUID id) {
        return articleDao.findById(id)
                .orElseThrow(() -> notFound("Article not found"));
    }

    @Override
    public ArticleResponseDTO getArticle(UUID id) {
        return articleMapper.toResponseDTO(getEntity(id));
    }

    @Override
    @Transactional
    public ArticleResponseDTO createArticle(ArticleRequestDTO articleDTO) {
        var article = articleMapper.toEntity(articleDTO);

        if (article.getArticleItems() != null) {
            article.getArticleItems().forEach(articleItem ->
                    articleItem.setArticle(article)
            );
        }

        return articleMapper.toResponseDTO(
                articleDao.save(article)
        );
    }

    @Override
    @Transactional
    public ArticleResponseDTO updateArticle(UUID id, ArticleRequestDTO articleDTO) {
        if (!articleDTO.id().equals(id)) {
            throw conflict("Article id in the body does not match the id in the URL");
        }

        if (articleDao.findById(id).isEmpty()) {
            throw notFound("Article not found");
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
