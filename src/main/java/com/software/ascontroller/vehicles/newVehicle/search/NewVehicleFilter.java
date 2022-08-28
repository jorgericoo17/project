package com.software.ascontroller.vehicles.newVehicle.search;

import com.software.ascontroller.model.entities.Model;
import com.software.ascontroller.status.entities.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewVehicleFilter {

    private String chassisNumber;
    private String plate;
    private Long idModel;
    private Long idStatus;

}
