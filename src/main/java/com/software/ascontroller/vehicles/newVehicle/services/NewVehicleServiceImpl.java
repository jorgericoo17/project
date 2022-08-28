package com.software.ascontroller.vehicles.newVehicle.services;

import com.software.ascontroller.vehicles.newVehicle.entities.NewVehicle;
import com.software.ascontroller.vehicles.newVehicle.repositories.NewVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
