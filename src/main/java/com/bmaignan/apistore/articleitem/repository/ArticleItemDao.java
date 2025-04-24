package com.bmaignan.apistore.articleitem.repository;

import com.bmaignan.apistore.articleitem.model.ArticleItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArticleItemDao extends JpaRepository<ArticleItem, UUID> {
}
