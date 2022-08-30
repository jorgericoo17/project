package com.software.ascontroller.sells.services;

import com.software.ascontroller.sells.dtos.SellDTO;
import com.software.ascontroller.sells.entities.Sell;

import java.util.List;
import java.util.Optional;

public interface SellService {

    public Sell getSellFromDTO(SellDTO sellDTO);
    Sell save(Sell sell);

    Optional<Sell> findById(Long idSell);

    List<Sell> findAll();

    void delete(Sell sell);
    void deleteByIdNewVehicle(Long idNewVehicle);

    List<Sell> findByIdUser(Long idUser);
}
