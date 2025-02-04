package com.bmaignan.apistore.article.repository;

import com.bmaignan.apistore.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDao extends JpaRepository<Article, Long> {
}
