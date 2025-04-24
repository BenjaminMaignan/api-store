package com.bmaignan.apistore.article.service;

import com.bmaignan.apistore.article.model.Article;
import com.bmaignan.apistore.article.repository.ArticleDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    @InjectMocks
    private ArticleService articleService;

    @Mock
    private ArticleDao articleDao;

    private Article article1;
    private Article article2;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("should find all articles")
    void shouldFindAllArticles() {
        // Given
        List<Article> articles = List.of(
                article1,
                article2
        );

        // When
        when(articleDao.findAll()).thenReturn(articles);

        // Then
        assertEquals(articles, articleService.findAllArticles());
    }
}

