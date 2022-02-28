package com.yakzhanov.flatseeker.model;

import lombok.Getter;

public enum LocationStatus {

    CLOSE("~20 мин"),
    MEDIUM("~40 мин"),
    LONG_AWAY(">40 мин"),
    FREE_WAY_CLOSE("Трасса ~20 мин"),
    FREE_WAY_MEDIUM("Трасса ~40 мин"),
    ;

    @Getter
    private final String description;

    LocationStatus(String description) {
        this.description = description;
    }
}
