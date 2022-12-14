package com.software.ascontroller.sells.repositories;

import com.software.ascontroller.sells.entities.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellRepository extends JpaRepository<Sell, Long> {

    @Query(value = "DELETE FROM sell WHERE IDNEW_VEHICLE = :idNewVehicle", nativeQuery = true)
    void deleteByIdNewVehicle(@Param("") Long idNewVechile);

    List<Sell> findBySellerIdUser(Long idUser);
}
