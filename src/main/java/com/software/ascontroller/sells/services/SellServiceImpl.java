package com.software.ascontroller.sells.services;

import com.software.ascontroller.sells.dtos.SellDTO;
import com.software.ascontroller.sells.entities.Sell;
import com.software.ascontroller.sells.repositories.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SellServiceImpl implements SellService{
    @Autowired
    private SellRepository sellRepository;

    @Override
    public Sell getSellFromDTO(SellDTO sellDTO) {
        Sell sell = new Sell();
        if(sellDTO.getIdSell() != null) {
            sell.setIdSell(sellDTO.getIdSell());
        }
        sell.setSeller(sellDTO.getSeller());
        sell.setNewVehicle(sellDTO.getNewVehicle());
        sell.setBuyerName(sellDTO.getBuyerName());
        sell.setBuyerDni(sellDTO.getBuyerDni());
        sell.setProfit(sellDTO.getProfit());

        return sell;
    }

    @Override
    public Optional<Sell> findById(Long idSell) {
        return this.sellRepository.findById(idSell);
    }

    @Override
    @Transactional(readOnly = false)
    public Sell save(Sell sell) {
        return this.sellRepository.save(sell);
    }

    @Override
    public List<Sell> findAll() {
        return this.sellRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Sell sell) {
        this.sellRepository.delete(sell);
    }

    @Override
    public void deleteByIdNewVehicle(Long idNewVehicle) {
        this.sellRepository.deleteByIdNewVehicle(idNewVehicle);
    }

    @Override
    public List<Sell> findByIdUser(Long idUser) {
        return this.sellRepository.findBySellerIdUser(idUser);
    }
}
