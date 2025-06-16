package com.bmaignan.apistore.core.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;


public class GenericSpecification<T> implements Specification<T> {
    private final Criteria criteria;

    public GenericSpecification(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return switch (criteria.operation()) {
            case EQUAL -> criteriaBuilder.equal(root.get(criteria.key()), criteria.value());
            case LIKE ->
                    criteriaBuilder.like(root.get(criteria.key()), "%" + criteria.value().toString().toLowerCase() + "%");
            case GREATER_THAN ->
                    criteriaBuilder.greaterThan(root.get(criteria.key()), (Comparable) criteria.value());
            case LESS_THAN -> criteriaBuilder.lessThan(root.get(criteria.key()), (Comparable) criteria.value());
        };
    }
}
