package com.software.ascontroller.sells.services;

import com.software.ascontroller.sells.dtos.SellDTO;
import com.software.ascontroller.sells.entities.Sell;

import java.util.List;

public interface SellService {

    public Sell getSellFromDTO(SellDTO sellDTO);
    Sell save(Sell sell);

    List<Sell> findAll();

    void deleteByIdNewVehicle(Long idNewVehicle);
}
