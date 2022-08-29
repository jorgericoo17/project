package com.software.ascontroller.sells.entities;

import com.software.ascontroller.user.entites.User;
import com.software.ascontroller.vehicles.newVehicle.entities.NewVehicle;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="SELL")
@NamedQuery(name="Sell.findAll", query = "SELECT s FROM Sell s")
@Getter
@Setter
public class Sell implements Serializable {

    @Id
    @SequenceGenerator(name = "SELL_IDSELL_GENERATOR", sequenceName = "SEll_idSell_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDSELL", unique = true, nullable = false)
    private long idSell;

    @NotNull
    @Column(name = "BUYER_NAME")
    private String buyerName;

    @NotNull
    @Column(name = "BUYER_DNI")
    private String buyerDni;

    @Column(name = "PROFIT")
    private Double profit;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "IDSELLER")
    private User seller;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "IDVEHICLE")
    private NewVehicle newVehicle;

}
