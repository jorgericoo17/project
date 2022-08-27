package com.software.ascontroller.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;

public final class PredicateUtils {
    private static final String PERCENT = "%";

    private PredicateUtils() {
    }

    public static Predicate ilike(CriteriaBuilder builder, Expression<String> path, String value){
        String param = percentConcat(value);

        return builder.like(builder.lower(path), param.toLowerCase());
    }

    public static String percentConcat(String param) {
        return PERCENT.concat(String.valueOf(param)).concat(PERCENT);
    }
}
