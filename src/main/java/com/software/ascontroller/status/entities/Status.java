package com.software.ascontroller.status.entities;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="STATUS")
@NamedQuery(name="Status.findAll", query = "SELECT s FROM Status s")
@Getter
@Setter
public class Status implements Serializable {

    @Id
    @SequenceGenerator(name = "STATUS_IDSTATUS_GENERATOR", sequenceName = "Status_idStatus_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDSTATUS", unique = true, nullable = false)
    private Long idStatus;

    @NotNull
    @Column(name = "name")
    private String name;

}

