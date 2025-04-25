package com.bmaignan.apistore.article.service;

import com.bmaignan.apistore.article.dto.ArticleLightResponseDTO;
import com.bmaignan.apistore.article.dto.ArticleRequestDTO;
import com.bmaignan.apistore.article.dto.ArticleResponseDTO;
import com.bmaignan.apistore.article.mapper.ArticleMapper;
import com.bmaignan.apistore.article.model.Article;
import com.bmaignan.apistore.article.repository.ArticleDao;
import com.bmaignan.apistore.article.service.impl.ArticleServiceImpl;
import com.bmaignan.apistore.articleitem.dto.ArticleItemRequestDTO;
import com.bmaignan.apistore.articleitem.dto.ArticleItemResponseDTO;
import com.bmaignan.apistore.articleitem.model.ArticleItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    private ArticleDao articleDao;

    @Mock
    private ArticleMapper articleMapper;

    @InjectMocks
    private ArticleServiceImpl articleService;

    private UUID articleId;
    private UUID unknownId;

    private Article article;
    private ArticleLightResponseDTO articleLightResponseDTO;

    @BeforeEach
    void setUp() {
        articleId = UUID.randomUUID();
        unknownId = UUID.randomUUID();

        article = new Article();
        article.setId(articleId);
        article.setName("Test Article");
        article.setPrice(100.0F);

        List<ArticleItem> articleItems = new ArrayList<>();
        ArticleItem item = new ArticleItem();
        item.setId(UUID.randomUUID());
        item.setSize("Test Item");
        item.setColor("Red");
        item.setArticle(article);
        articleItems.add(item);
        article.setArticleItems(articleItems);

        articleLightResponseDTO = new ArticleLightResponseDTO(
                articleId,
                "Test Article",
                100.0F
        );
    }

    @Test
    void findAllArticles_shouldReturnAllArticles() {
        // Given
        List<Article> articles = List.of(article);

        // When
        when(articleDao.findAll()).thenReturn(articles);
        when(articleMapper.toLightResponseDTO(article)).thenReturn(articleLightResponseDTO);

        // Then
        List<ArticleLightResponseDTO> result = articleService.findAllArticles();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(articleLightResponseDTO, result.getFirst());
        verify(articleDao).findAll();
        verify(articleMapper).toLightResponseDTO(article);
    }

    @Test
    void getEntity_shouldReturnArticle() {
        // Given

        // When
        when(articleDao.findById(articleId)).thenReturn(java.util.Optional.of(article));

        // Then
        Article result = articleService.getEntity(articleId);
        assertNotNull(result);
        assertEquals(articleId, result.getId());
        verify(articleDao).findById(articleId);
    }

    @Test
    void getEntity_shouldThrowNotFound() {
        // Given

        // When
        when(articleDao.findById(unknownId)).thenReturn(java.util.Optional.empty());

        // Then
        assertThrows(RuntimeException.class, () -> articleService.getEntity(articleId));
    }

    @Test
    void getArticle_shouldReturnArticle() {
        // Given
        ArticleItemResponseDTO articleItemResponseDTO = new ArticleItemResponseDTO(
                UUID.randomUUID(),
                "Test Item",
                "Red",
                2
        );

        ArticleResponseDTO articleResponseDTO = new ArticleResponseDTO(
                articleId,
                "Test Article",
                100.0F,
                List.of(articleItemResponseDTO)
        );

        // When
        when(articleDao.findById(articleId)).thenReturn(java.util.Optional.of(article));
        when(articleMapper.toResponseDTO(article)).thenReturn(articleResponseDTO);

        // Then
        ArticleResponseDTO result = articleService.getArticle(articleId);

        assertNotNull(result);
        assertEquals(articleId, result.id());

        verify(articleDao).findById(articleId);
        verify(articleMapper).toResponseDTO(article);
    }

    @Test
    void getArticle_shouldThrowNotFound() {
        // Given

        // When
        when(articleDao.findById(unknownId)).thenReturn(java.util.Optional.empty());

        // Then
        assertThrows(RuntimeException.class, () -> articleService.getArticle(articleId));
    }

    @Test
    void createArticle_shouldCreateTheArticle() {
        // Given
        ArticleItemRequestDTO articleItemRequestDTO = new ArticleItemRequestDTO(
                null,
                null,
                "New Item",
                "Red",
                2
        );

        ArticleRequestDTO articleToCreate = new ArticleRequestDTO(
                null,
                "New Article",
                100.0F,
                List.of(articleItemRequestDTO)
        );

        // When
        when(articleMapper.toEntity(articleToCreate)).thenReturn(article);
        when(articleDao.save(article)).thenReturn(article);
        when(articleMapper.toResponseDTO(article)).thenReturn(new ArticleResponseDTO(
                articleId,
                "Test Article",
                100.0F,
                List.of(new ArticleItemResponseDTO(UUID.randomUUID(), "Test Item", "Red", 2))
        ));

        // Then
        ArticleResponseDTO result = articleService.createArticle(articleToCreate);

        assertNotNull(result);
        assertEquals(articleId, result.id());
        assertEquals("Test Article", result.name());
        assertEquals(100.0F, result.price());
        assertEquals(1, result.articleItems().size());

        verify(articleMapper).toEntity(articleToCreate);
        verify(articleDao).save(article);
        verify(articleMapper).toResponseDTO(article);
    }

    @Test
    void updateArticle_shouldUpdateTheArticle() {
        // Given
        ArticleRequestDTO articleToUpdate = new ArticleRequestDTO(
                articleId,
                "Updated Article",
                150.0F,
                List.of(new ArticleItemRequestDTO(UUID.randomUUID(), articleId, "Updated Item", "Blue", 3))
        );

        ArticleResponseDTO articleResponseDTO = new ArticleResponseDTO(
                articleId,
                "Updated Article",
                150.0F,
                List.of(new ArticleItemResponseDTO(UUID.randomUUID(), "Updated Item", "Blue", 3))
        );

        // When
        when(articleMapper.toEntity(articleToUpdate)).thenReturn(article);
        when(articleDao.save(article)).thenReturn(article);
        when(articleMapper.toResponseDTO(article)).thenReturn(articleResponseDTO);

        // Then
        ArticleResponseDTO result = articleService.updateArticle(articleId, articleToUpdate);

        assertNotNull(result);
        assertEquals(articleId, result.id());
        assertEquals("Updated Article", result.name());
        assertEquals(150.0F, result.price());
        assertEquals(1, result.articleItems().size());

        verify(articleMapper).toEntity(articleToUpdate);
        verify(articleDao).save(article);
        verify(articleMapper).toResponseDTO(article);
    }

    @Test
    void updateArticle_shouldThrowConflict() {
        // Given
        ArticleRequestDTO articleToUpdate = new ArticleRequestDTO(
                UUID.randomUUID(),
                "Updated Article",
                150.0F,
                List.of(new ArticleItemRequestDTO(UUID.randomUUID(), articleId, "Updated Item", "Blue", 3))
        );

        // When

        // Then
        assertThrows(RuntimeException.class, () -> articleService.updateArticle(articleId, articleToUpdate));
    }

    @Test
    void deleteArticle_shouldDeleteTheArticle() {
        // Given

        // When
        articleService.deleteArticle(articleId);

        // Then
        verify(articleDao).deleteById(articleId);
    }
}

