package com.software.ascontroller.model.services;

import com.software.ascontroller.model.dtos.ModelDTO;
import com.software.ascontroller.model.entities.Model;
import com.software.ascontroller.model.search.ModelFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ModelService {

    Optional<Model> findById(Long idModel);

    List<Model> findAll();
    Page<Model> findAll(ModelFilter modelFilter, Pageable pageable);

    Model getModelFromDTO(ModelDTO modelDTO);

    Model save(Model model);

    void delete(Model model);
}
