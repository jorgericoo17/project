package com.software.ascontroller.vehicles.newVehicle.dtos;


import com.software.ascontroller.model.entities.Model;
import com.software.ascontroller.status.entities.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NewVehicleDTO {

    Long idNewVehicle;
    private String chassisNumber;
    private String plate;
    private Double sellPrice;
    private Model model;
    private Status status;
}
