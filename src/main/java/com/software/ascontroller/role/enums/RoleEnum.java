package com.software.ascontroller.role.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN(1L,"ADMIN"),
    SELLER(2L,"SELLER"),
    MANAGER(3L,"MANAGER");

    private Long idRole;
    private String name;

    private RoleEnum (Long idRole, String name) {
        this.idRole = idRole;
        this.name = name;
    }
}
