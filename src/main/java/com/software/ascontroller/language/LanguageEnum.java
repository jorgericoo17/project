package com.software.ascontroller.language;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum LanguageEnum {

    ESPANHOL("Español", "es", 1L),
    GALEGO("Galego", "ga", 2L);
    private String name;
    private String code;
    private Long id;

    private LanguageEnum(String name, String code, Long id) {
        this.name = name;
        this.code = code;
        this.id = id;
    }
}
