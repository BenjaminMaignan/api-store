package com.bmaignan.apistore.core.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationBuilder<T> {
    private final List<Criteria> params = new ArrayList<>();

    public SpecificationBuilder<T> with(String key, Object value, Operation operation) {
        if (value != null) {
            params.add(new Criteria(key, value, operation));
        }
        return this;
    }

    public Specification<T> build() {
        Specification<T> result = Specification.where(null);

        for (Criteria param : params) {
            result = result.and(new GenericSpecification<>(param));
        }

        return result;
    }
}
