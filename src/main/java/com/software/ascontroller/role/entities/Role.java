package com.software.ascontroller.role.entities;

import com.sun.istack.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name="ROLE")
@Getter
public class Role {

    @Id
    @Column(name = "IDROLE")
    private Long idRole;

    @NotNull
    @Column(name = "NAME")
    private String name;
}
