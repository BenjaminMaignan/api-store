package com.bmaignan.apistore.article.specification;

import com.bmaignan.apistore.article.criteria.ArticleCriteria;
import com.bmaignan.apistore.article.model.Article;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ArticleSpecification {
    private static Specification<Article> nameLike(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");

    }

    public static Specification<Article> build(ArticleCriteria criteria) {
        Specification<Article> spec = Specification.where(null);

        if (criteria == null) {
            return spec;
        }

        if (criteria.getName() != null && !criteria.getName().isEmpty()) {
            spec = spec.and(nameLike(criteria.getName()));
        }

        return spec;

    }
}
