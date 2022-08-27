package com.software.ascontroller.vehicles.usedVehicle.entities;

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
@Table(name="USED_VEHICLE")
@NamedQuery(name="UsedVehicle.findAll", query="SELECT uv FROM UsedVehicle uv")
@Getter
@Setter
public class UsedVehicle implements Serializable {

    @Id
    @SequenceGenerator(name = "USED_VEHICLE_IDUSED_VEHICLE", sequenceName = "UsedVehicle_idUsedVehicle_SEQ", allocationSize = 1)
    @Column(name = "IDUSED_VEHICLE", unique = true, nullable = false)
    private Long idUsedVehicle;

    @NotNull
    @Column(name = "CHASSIS_NUMBER")
    private String chassisNumber;

    @NotNull
    @Column(name = "PLATE")
    private String plate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACQUISITION_DATE")
    private Date acquisistionDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SELLING_DATE")
    private Date sellingDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PLATING_DATE")
    private Date platingDate;

    @Column(name = "COST")
    private Double cost;

    @Column(name = "PVP")
    private double pvp;

    @Column(name = "BILLS")
    private double bills;

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
    private User user;
}
