package com.software.ascontroller.vehicles.newVehicle.services;

import com.software.ascontroller.vehicles.newVehicle.entities.NewVehicle;

import java.util.List;
import java.util.Optional;

public interface NewVehicleService {

    List<NewVehicle> findAll();

    List<NewVehicle> findAllInStock();

    Optional<NewVehicle> findById(Long idNewVehicle);

}
