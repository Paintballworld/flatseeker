package com.yakzhanov.flatseeker.model;

import lombok.Getter;

public enum BathroomStatus {

    SINGLE ("Одна"),
    DOUBLE ("Две"),
    SINGLE_NO_SHOWER ("Одна (без ванной)"),
    DOUBLE_NO_SHOWER ("Две (без ванной)");

    @Getter
    private final String title;

    BathroomStatus(String title) {
        this.title = title;
    }
}
