package com.software.ascontroller.vehicles.newVehicle.repositories;

import com.software.ascontroller.vehicles.newVehicle.entities.NewVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewVehicleRepository extends JpaRepository<NewVehicle, Long> {

    @Query("FROM NewVehicle nv WHERE nv.status.idStatus NOT LIKE 3")
    List<NewVehicle> findAllInStock();

    @Query("FROM NewVehicle nv WHERE nv.status.idStatus LIKE 3")
    List<NewVehicle> findAllSold();
}
