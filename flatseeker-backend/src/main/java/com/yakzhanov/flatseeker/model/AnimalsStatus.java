package com.yakzhanov.flatseeker.model;

import lombok.Getter;

public enum AnimalsStatus {

    ALLOWED ("Разрешены"),
    NOT_ALLOWED ("Запрещены"),
    NOT_DEFINED ("Не указано");

    @Getter
    private final String title;

    AnimalsStatus(String title) {
        this.title = title;
    }
}
