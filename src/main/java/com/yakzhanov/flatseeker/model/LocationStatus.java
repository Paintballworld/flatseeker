package com.yakzhanov.flatseeker.model;

import lombok.Getter;

public enum LocationStatus {

    CLOSE("~20 min"),
    MEDIUM("~40 min"),
    LONG_AWAY(">40 min"),
    FREE_WAY_CLOSE("Freeway ~20 min"),
    FREE_WAY_MEDIUM("Freeway ~40 min"),
    ;

    @Getter
    private final String description;

    LocationStatus(String description) {
        this.description = description;
    }
}
