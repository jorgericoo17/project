package com.software.ascontroller.vehicles.newVehicle.entities;

import com.software.ascontroller.model.entities.Model;
import com.software.ascontroller.status.entities.Status;
import com.software.ascontroller.user.entites.User;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="NEW_VEHICLE")
@NamedQuery(name="NewVehicle.findAll", query="SELECT nv FROM NewVehicle nv")
@Getter
@Setter
public class NewVehicle implements Serializable {

    @Id
    @SequenceGenerator(name = "NEW_VEHICLE_IDNEW_VEHICLE", sequenceName = "NewVehicle_idNewVehicle_SEQ", allocationSize = 1)
    @Column(name = "IDNEW_VEHICLE", unique = true, nullable = false)
    private Long idNewVehicle;

    @NotNull
    @Column(name = "CHASSIS_NUMBER")
    private String chassisNumber;

    @NotNull
    @Column(name = "PLATE")
    private String plate;

    @Column(name = "SELL_PRICE")
    private Double sellPrice;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BILLING_DATE")
    private Date billingDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SELLING_DATE")
    private Date sellingDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "IDMODEL")
    private Model model;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "IDSTATUS")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "IDSELLER")
    private User seller;
}
