package com.software.ascontroller.model.search;

import com.software.ascontroller.model.entities.Model;
import com.software.ascontroller.user.entites.User;
import com.software.ascontroller.utils.SpecificationUtils;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ModelSpecifications {

    private ModelSpecifications() {
        //Empty method
    }

    public static Specification<com.software.ascontroller.model.entities.Model> filterBy(final ModelFilter modelFilter) {

        return new Specification<Model>() {

            @Override
            public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

                List<Predicate> predicates = new ArrayList<>();
                this.addPredicates(root, builder, predicates);

                return builder.and(predicates.toArray(new Predicate[] {}));
            }

            private void addPredicates(Root<Model> root,
                                       CriteriaBuilder builder,
                                       List<Predicate> predicates) {

                this.addPredicateIfNotEmpty(modelFilter.getName(),"name", root, builder, predicates);
                this.addPredicateIfNotEmpty(modelFilter.getFinish(), "finish", root, builder, predicates);
                this.addPredicateIfNotNull(modelFilter.getYear(), "year", root, builder, predicates);
            }

            private void addPredicateIfNotEmpty(String field,
                                                String name,
                                                Root<Model> root,
                                                CriteriaBuilder builder,
                                                List<Predicate> predicates) {

                if (!StringUtils.isEmpty(field)) {
                    SpecificationUtils.addPredicateFor(name, field, root, builder, predicates);
                }
            }

            private void addPredicateIfNotNull(Integer field,
                                                String name,
                                                Root<Model> root,
                                                CriteriaBuilder builder,
                                                List<Predicate> predicates) {

                if (field != null) {
                    SpecificationUtils.addPredicateFor(name, field.toString(), root, builder, predicates);
                }
            }
        };
    }
}
