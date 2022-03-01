package com.yakzhanov.flatseeker.model;

import lombok.Getter;

public enum ApartmentType {

    FLAT ("Квартира"),
    HOUSE ("Дом"),
    PART_OF_HOUSE ("Часть дома");

    @Getter
    private final String title;

    ApartmentType(String title) {
        this.title = title;
    }
}
