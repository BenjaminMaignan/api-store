package com.bmaignan.apistore.articleitem.model;

import com.bmaignan.apistore.article.model.Article;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String size;

    private String color;

    private Integer availableStock;

    @OneToOne
    @JoinColumn(name = "article_id")
    private Article article;
}
