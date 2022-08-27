package com.software.ascontroller.model.services;

import com.software.ascontroller.model.dtos.ModelDTO;
import com.software.ascontroller.model.entities.Model;
import com.software.ascontroller.model.repositories.ModelRepository;
import com.software.ascontroller.model.search.ModelFilter;
import com.software.ascontroller.model.search.ModelSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public Optional<Model> findById(Long idModel) {
        return this.modelRepository.findById(idModel);
    }
    @Override
    public List<Model> findAll() {
        return this.modelRepository.findAll();
    }

    @Override
    public Page<Model> findAll(ModelFilter modelFilter, Pageable pageable) {
        return this.modelRepository.findAll(ModelSpecifications.filterBy(modelFilter), pageable);
    }

    @Override
    public Model getModelFromDTO(ModelDTO modelDTO) {
        Model model = new Model();

        if(modelDTO.getIdModel() != null) {
            model.setIdModel(modelDTO.getIdModel());
        }
        model.setName(modelDTO.getName());
        model.setFinish(modelDTO.getFinish());
        model.setYear(modelDTO.getYear());

        return model;
    }

    @Override
    @Transactional(readOnly = false)
    public Model save(Model model) {
        return this.modelRepository.save(model);
    }
}
