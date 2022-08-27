package com.software.ascontroller.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.util.StringUtils;

import java.util.List;

public final class SpecificationUtils {

    private SpecificationUtils() {
        // Empty constructor
    }

    public static void addPredicateFor(String param, String value, Root<?> root, CriteriaBuilder builder, List<Predicate> predicates) {
        if(StringUtils.hasText(value)){
            Expression<String> path = root.get(param).as(String.class);
            predicates.add(PredicateUtils.ilike(builder, path, PredicateUtils.percentConcat(value)));
        }
    }
}