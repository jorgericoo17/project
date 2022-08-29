package com.software.ascontroller.vehicles.newVehicle.services;

import com.software.ascontroller.vehicles.newVehicle.dtos.NewVehicleDTO;
import com.software.ascontroller.vehicles.newVehicle.entities.NewVehicle;
import com.software.ascontroller.vehicles.newVehicle.repositories.NewVehicleRepository;
import com.software.ascontroller.vehicles.newVehicle.search.NewVehicleFilter;
import com.software.ascontroller.vehicles.newVehicle.search.NewVehicleSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class NewVehicleServiceImpl implements NewVehicleService{

    @Autowired
    private NewVehicleRepository newVehicleRepository;

    @Override
    public List<NewVehicle> findAll() {
        return this.newVehicleRepository.findAll();
    }

    @Override
    public List<NewVehicle> findAllInStock() {
        return this.newVehicleRepository.findAllInStock();
    }

    @Override
    public Optional<NewVehicle> findById(Long idNewVehicle) {
        return this.newVehicleRepository.findById(idNewVehicle);
    }

    @Override
    @Transactional(readOnly = false)
    public NewVehicle save(NewVehicle newVehicle) {
        return this.newVehicleRepository.save(newVehicle);
    }

    @Override
    public NewVehicle getNewVehicleFromDTO(NewVehicleDTO newVehicleDTO) {
        NewVehicle newVehicle = new NewVehicle();
        if(newVehicleDTO.getIdNewVehicle() != null) {
            newVehicle.setIdNewVehicle(newVehicleDTO.getIdNewVehicle());
        }
        newVehicle.setChassisNumber(newVehicleDTO.getChassisNumber());
        newVehicle.setPlate(newVehicleDTO.getPlate());
        newVehicle.setSellPrice(newVehicleDTO.getSellPrice());
        newVehicle.setModel(newVehicleDTO.getModel());
        newVehicle.setCost((newVehicleDTO.getCost()));
        newVehicle.setStatus(newVehicleDTO.getStatus());
        newVehicle.setIsNew(newVehicleDTO.getIsNew());

        return newVehicle;
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(NewVehicle newVehicle) {
        this.newVehicleRepository.delete(newVehicle);
    }

    @Override
    public List<NewVehicle> findByIdModel(Long idModel) {
        return this.newVehicleRepository.findByIdModel(idModel);
    }

    @Override
    public Page<NewVehicle> findAll(NewVehicleFilter newVehicleFilter, Pageable pageable) {
        return this.newVehicleRepository.findAll(NewVehicleSpecifications.filterBy(newVehicleFilter), pageable);
    }
}
