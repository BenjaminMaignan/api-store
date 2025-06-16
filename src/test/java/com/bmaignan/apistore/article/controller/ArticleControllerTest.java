package com.bmaignan.apistore.article.controller;

import com.bmaignan.apistore.article.model.Article;
import com.bmaignan.apistore.article.repository.ArticleDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArticleDao articleDao;

    private UUID articleId1;
    private UUID unknownId;

    @BeforeEach
    void setUp() {
        articleDao.deleteAll();

        Article article1 = new Article();
        article1.setName("Test Article");
        article1.setPrice(100.0F);
        article1 = articleDao.save(article1);
        articleId1 = article1.getId();

        Article article2 = new Article();
        article2.setName("Another Article");
        article2.setPrice(150.0F);
        articleDao.save(article2);

        unknownId = UUID.randomUUID();

    }

    @Test
    void getAllArticles_shouldReturnListOfArticles() throws Exception {
        mockMvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(articleId1.toString()))
                .andExpect(jsonPath("$[0].name").value("Test Article"))
                .andExpect(jsonPath("$[0].price").value(100.0))
                .andExpect(jsonPath("$[1].name").value("Another Article"))
                .andExpect(jsonPath("$[1].price").value(150.0));
    }

    @Test
    void getArticleById_shouldReturnArticle() throws Exception {
        mockMvc.perform(get("/api/articles/{id}", articleId1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Article"))
                .andExpect(jsonPath("$.price").value(100.0));
    }

    @Test
    void getArticleByUnknownId_shouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/articles/{id}", unknownId))
                .andExpect(status().isNotFound());
    }

    @Test
    void createArticle_shouldReturnCreatedArticle() throws Exception {
        String newArticleJson = """
                {
                    "name": "New Article",
                    "price": 200.0,
                    "articleItems": []
                }
                """;

        mockMvc.perform(post("/api/articles")
                        .contentType("application/json")
                        .content(newArticleJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Article"))
                .andExpect(jsonPath("$.price").value(200.0));

        assertThat(articleDao.findAll()).hasSize(3);
    }

    @Test
    void updateArticle_shouldReturnUpdatedArticle() throws Exception {
        String updatedArticleJson = """
                {
                    "id": "%s",
                    "name": "Updated Article",
                    "price": 250.0,
                    "articleItems": []
                }
                """.formatted(articleId1);

        mockMvc.perform(put("/api/articles/" + articleId1)
                        .contentType("application/json")
                        .content(updatedArticleJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Article"))
                .andExpect(jsonPath("$.price").value(250.0));
    }

    @Test
    void updateArticleWithWrongData_shouldReturnConflict() throws Exception {
        String updatedArticleJson = """
                {
                    "id": "%s",
                    "name": "Updated Article",
                    "price": 250.0,
                    "articleItems": []
                }
                """.formatted(unknownId);

        mockMvc.perform(put("/api/articles/" + articleId1)
                        .contentType("application/json")
                        .content(updatedArticleJson))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.title").value("Conflict"))
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.detail").value("Article id in the body does not match the id in the URL"));
    }

    @Test
    void deleteArticle_shouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/articles/" + articleId1))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/articles/" + articleId1))
                .andExpect(status().isNotFound());
    }
}
