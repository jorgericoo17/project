package com.software.ascontroller.vehicles.newVehicle.search;

import com.software.ascontroller.model.entities.Model;
import com.software.ascontroller.status.entities.Status;
import com.software.ascontroller.utils.SpecificationUtils;
import com.software.ascontroller.vehicles.newVehicle.entities.NewVehicle;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class NewVehicleSpecifications {

    private NewVehicleSpecifications() {
        //Empty construct
    }

    public static Specification<NewVehicle> filterBy(final NewVehicleFilter filter) {

        return new Specification<NewVehicle>() {
            @Override
            public Predicate toPredicate(Root<NewVehicle> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

                List<Predicate> predicates = new ArrayList<>();
                this.addPredicates(root, builder, predicates);

                return builder.and(predicates.toArray(new Predicate[] {}));
            }

            private void addPredicates(Root<NewVehicle> root,
                                       CriteriaBuilder builder,
                                       List<Predicate> predicates) {

                this.addPredicateIfNotEmpty(filter.getChassisNumber(),"chassisNumber", root, builder, predicates);
                this.addPredicateIfNotEmpty(filter.getPlate(), "plate", root, builder, predicates);
                if(filter.getIsNew() != null) {
                    predicates.add(builder.equal(root.get("isNew"), filter.getIsNew()));
                }
                this.addJoinPredicate(filter, root, builder, predicates);
            }

            private void addPredicateIfNotEmpty(String field,
                                                String name,
                                                Root<NewVehicle> root,
                                                CriteriaBuilder builder,
                                                List<Predicate> predicates) {

                if (!StringUtils.isEmpty(field)) {
                    SpecificationUtils.addPredicateFor(name, field, root, builder, predicates);
                }
            }

            private void addJoinPredicate(NewVehicleFilter filter,
                                          Root<NewVehicle> root,
                                          CriteriaBuilder builder,
                                          List<Predicate> predicates) {

                if(!StringUtils.isEmpty(filter.getIdModel())) {
                    Join<NewVehicle, Model> model = root.join("model");
                    predicates.add(builder.equal(model.get("idModel"), filter.getIdModel()));
                }
                if (!StringUtils.isEmpty(filter.getIdModel()))  {
                    Join<NewVehicle, Status> status = root.join("status");
                    predicates.add(builder.and(builder.equal(status.get("idStatus"), filter.getIdStatus()),builder.notEqual(status.get("idStatus"),3)));
                }
            }
        };
    }
}

