package com.bmaignan.apistore.article.service;

import com.bmaignan.apistore.article.repository.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ArticleServiceIntegrationTest {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleDao articleDao;

}
