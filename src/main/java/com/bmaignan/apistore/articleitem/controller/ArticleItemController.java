package com.bmaignan.apistore.articleitem.controller;

import com.bmaignan.apistore.articleitem.dto.ArticleItemResponseDTO;
import com.bmaignan.apistore.articleitem.service.ArticleItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/article-items")
public class ArticleItemController {
    private final ArticleItemService articleItemService;

    public ArticleItemController(ArticleItemService articleItemService) {
        this.articleItemService = articleItemService;
    }

    @GetMapping
    public List<ArticleItemResponseDTO> getArticles() {
        return articleItemService.findAllArticleItems();
    }
}
