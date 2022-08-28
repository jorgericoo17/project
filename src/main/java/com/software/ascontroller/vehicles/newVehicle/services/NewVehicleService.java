package com.software.ascontroller.vehicles.newVehicle.services;

import com.software.ascontroller.vehicles.newVehicle.dtos.NewVehicleDTO;
import com.software.ascontroller.vehicles.newVehicle.entities.NewVehicle;

import java.util.List;
import java.util.Optional;

public interface NewVehicleService {

    List<NewVehicle> findAll();

    List<NewVehicle> findAllInStock();

    Optional<NewVehicle> findById(Long idNewVehicle);

    NewVehicle save(NewVehicle newVehicle);

    NewVehicle getNewVehicleFromDTO(NewVehicleDTO newVehicleDTO);

    void delete(NewVehicle newVehicle);

    List<NewVehicle> findByIdModel(Long idModel);
}
