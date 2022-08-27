package com.software.ascontroller.model.repositories;

import com.software.ascontroller.model.entities.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    Page<Model> findAll(Specification<Model> specification, Pageable pageable);
}
