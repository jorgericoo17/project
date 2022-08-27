package com.software.ascontroller.vehicles.newVehicle.dtos;


import com.software.ascontroller.model.entities.Model;
import com.software.ascontroller.status.entities.Status;
import com.software.ascontroller.user.entites.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class newVehicleDTO {

    private String chassisNumber;
    private String plate;
    private Date billingDate;
    private Date sellingDate;
    private Model model;
    private Status status;

}
