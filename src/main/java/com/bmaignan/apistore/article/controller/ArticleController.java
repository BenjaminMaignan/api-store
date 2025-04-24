package com.bmaignan.apistore.article.controller;

import com.bmaignan.apistore.article.dto.ArticleRequestDTO;
import com.bmaignan.apistore.article.dto.ArticleResponseLightDTO;
import com.bmaignan.apistore.article.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<ArticleResponseLightDTO> getArticles() {
        return articleService.findAllArticles();
    }

    @GetMapping("/{id}")
    public ArticleResponseLightDTO getArticle(@PathVariable UUID id) {
        return articleService.getArticle(id);
    }

    @PostMapping
    public ArticleResponseLightDTO createArticle(@RequestBody ArticleRequestDTO article) {
        return articleService.createArticle(article);
    }

    @PutMapping("/{id}")
    public ArticleResponseLightDTO updateArticle(@PathVariable UUID id, @RequestBody ArticleRequestDTO article) {
        return articleService.updateArticle(id, article);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable UUID id) {
        articleService.deleteArticle(id);
    }
}
