package com.software.ascontroller.sells.dtos;

import com.software.ascontroller.user.entites.User;
import com.software.ascontroller.vehicles.newVehicle.entities.NewVehicle;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SellDTO {

    private Long idSell;
    private String buyerName;
    private String buyerDni;
    private Double profit;
    private User seller;
    private NewVehicle newVehicle;

}
