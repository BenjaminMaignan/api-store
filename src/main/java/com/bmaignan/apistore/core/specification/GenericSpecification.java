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
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return switch (criteria.operation()) {
            case EQUAL -> cb.equal(root.get(criteria.key()), criteria.value());
            case LIKE ->
                    cb.like(cb.lower(root.get(criteria.key()).as(String.class)), "%" + criteria.value().toString().toLowerCase() + "%");
            case GREATER_THAN ->
                    cb.greaterThan(root.get(criteria.key()), (Comparable) criteria.value());
            case LESS_THAN -> cb.lessThan(root.get(criteria.key()), (Comparable) criteria.value());
        };
    }
}
