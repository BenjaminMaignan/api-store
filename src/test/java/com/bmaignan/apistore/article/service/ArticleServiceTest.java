package com.bmaignan.apistore.article.service;

import com.bmaignan.apistore.article.model.Article;
import com.bmaignan.apistore.article.repository.ArticleDao;
import com.bmaignan.apistore.articleitem.model.ArticleItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    @InjectMocks
    private ArticleService articleService;

    @Mock
    private ArticleDao articleDao;

    private final UUID articleId1 = UUID.fromString("8db9588e-cc23-43d5-9ce3-acf23117da9d");

    private Article article1;

    private ArticleItem articleItem1;

    @BeforeEach
    void setUp() {
        article1 = new Article();
        articleItem1 = new ArticleItem();

        articleItem1.setId(UUID.fromString("a1b2c3d4-e5f6-7g8h-9i0j-k1l2m3n4o5p6"));
        articleItem1.setSize("XL");
        articleItem1.setColor("Red");
        articleItem1.setAvailableStock(10);
        articleItem1.setArticle(article1);


        article1.setId(articleId1);
        article1.setName("Article 1");
        article1.setPrice(15.99F);
        article1.setArticleItems(List.of(articleItem1));
    }


    @Test
    @DisplayName("Get Article by ID")
    void shouldGetArticleByID() {
        // When
        when(articleDao.findById(articleId1)).thenReturn(java.util.Optional.of(article1));

        // Then
        Article result = articleService.getEntity(articleId1);
        assertEquals(articleId1, result.getId());
    }
}

