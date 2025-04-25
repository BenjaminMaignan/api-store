package com.bmaignan.apistore.article.controller;

import com.bmaignan.apistore.article.dto.ArticleLightResponseDTO;
import com.bmaignan.apistore.article.dto.ArticleRequestDTO;
import com.bmaignan.apistore.article.dto.ArticleResponseDTO;
import com.bmaignan.apistore.article.service.ArticleService;
import com.bmaignan.apistore.articleitem.dto.ArticleItemRequestDTO;
import com.bmaignan.apistore.articleitem.dto.ArticleItemResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testcontainers.shaded.org.hamcrest.Matchers.hasSize;

@WebMvcTest(ArticleController.class)
@ExtendWith(MockitoExtension.class)
class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ArticleService articleService;

    private UUID articleId;
    private ArticleRequestDTO articleRequestDTO;
    private ArticleResponseDTO articleResponseDTO;
    private List<ArticleLightResponseDTO> articleList;

    @BeforeEach
    void setUp() {
        articleId = UUID.randomUUID();

        articleRequestDTO = new ArticleRequestDTO(
                articleId,
                "Test Article",
                100.0F,
                List.of(
                        new ArticleItemRequestDTO(UUID.randomUUID(), articleId, "XL", "Red", 2),
                        new ArticleItemRequestDTO(UUID.randomUUID(), articleId, "XL", "Blue", 2)
                )
        );

        articleResponseDTO = new ArticleResponseDTO(
                articleId,
                "Test Article",
                100.0F,
                List.of(
                        new ArticleItemResponseDTO(UUID.randomUUID(), "XL", "Red", 2),
                        new ArticleItemResponseDTO(UUID.randomUUID(), "XL", "Blue", 2)
                )
        );

        articleList = List.of(
                new ArticleLightResponseDTO(articleId, "Test Article", 100.0F),
                new ArticleLightResponseDTO(UUID.randomUUID(), "Another Article", 150.0F)
        );
    }

    @Test
    void getAllArticles_shouldReturnListOfArticles() throws Exception {
        // Given
        when(articleService.findAllArticles()).thenReturn(articleList);

        // When/Then
        mockMvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(articleId.toString()))
                .andExpect(jsonPath("$[0].name").value("Test Article"))
                .andExpect(jsonPath("$[0].price").value(100.0));

        verify(articleService).findAllArticles();
    }


}
