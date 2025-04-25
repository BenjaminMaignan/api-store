package com.bmaignan.apistore.article.controller;

import com.bmaignan.apistore.article.model.Article;
import com.bmaignan.apistore.article.repository.ArticleDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import java.util.UUID;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.ANY)
@ActiveProfiles("test")
class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArticleDao articleDao;

    private UUID articleId1;
    private UUID articleId2;
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
        article2 = articleDao.save(article2);
        articleId2 = article2.getId();

        unknownId = UUID.randomUUID();

    }

    @Test
    void getAllArticles_shouldReturnListOfArticles() throws Exception {
        mockMvc.perform(get("/api/articles")
                        .accept(MediaType.APPLICATION_JSON))
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
        mockMvc.perform(get("/api/articles/" + articleId1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Article"))
                .andExpect(jsonPath("$.price").value(100.0));
    }

    @Test
    void getArticleByUnknownId_shouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/articles/" + unknownId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
