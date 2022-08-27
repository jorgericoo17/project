package com.software.ascontroller.status.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {
    DISPONIBLE(1L, "DISPONIBLE"),
    RESERVADO(2L, "RESERVADO"),
    VENDIDO(3L, "VENDIDO");

    private Long idStatus;
    private String name;

    private StatusEnum(Long idStatus, String name) {
        this.idStatus = idStatus;
        this.name = name;
    }
}
