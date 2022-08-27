package com.software.ascontroller.user.search;

import com.software.ascontroller.role.entities.Role;
import com.software.ascontroller.user.entites.User;
import com.software.ascontroller.utils.SpecificationUtils;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserSpecifications {

    private UserSpecifications() {
        //Empty construct
    }

    public static Specification<User> filterBy(final UserFilter userFilter) {

        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

                List<Predicate> predicates = new ArrayList<>();
                this.addPredicates(root, builder, predicates);

                return builder.and(predicates.toArray(new Predicate[] {}));
            }

            private void addPredicates(Root<User> root,
                                       CriteriaBuilder builder,
                                       List<Predicate> predicates) {

                this.addPredicateIfNotEmpty(userFilter.getUsername(),"username", root, builder, predicates);
                this.addPredicateIfNotEmpty(userFilter.getName(), "name", root, builder, predicates);
                this.addPredicateIfNotEmpty(userFilter.getLastName(), "lastName", root, builder, predicates);
                this.addJoinPredicate(userFilter, root, builder, predicates);
            }

            private void addPredicateIfNotEmpty(String field,
                                                String name,
                                                Root<User> root,
                                                CriteriaBuilder builder,
                                                List<Predicate> predicates) {

                if (!StringUtils.isEmpty(field)) {
                    SpecificationUtils.addPredicateFor(name, field, root, builder, predicates);
                }
            }

            private void addJoinPredicate(UserFilter userFilter,
                                     Root<User> root,
                                     CriteriaBuilder builder,
                                     List<Predicate> predicates) {

                if(!StringUtils.isEmpty(userFilter.getIdRole())) {
                    Join<User, Role> role = root.join("role");
                    predicates.add(builder.equal(role.get("idRole"), userFilter.getIdRole()));
                }
            }
        };
    }
}
