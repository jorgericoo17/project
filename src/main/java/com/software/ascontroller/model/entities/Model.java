package com.software.ascontroller.model.entities;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="MODEL")
@NamedQuery(name="Model.findAll", query = "SELECT m FROM Model m")
@Getter
@Setter
public class Model implements Serializable {

    @Id
    @SequenceGenerator(name = "MODEL_IDMODEL_GENERATOR", sequenceName = "Model_idModel_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDMODEL", unique = true, nullable = false)
    private Long idModel;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name="FINISH")
    private String finish;

}
