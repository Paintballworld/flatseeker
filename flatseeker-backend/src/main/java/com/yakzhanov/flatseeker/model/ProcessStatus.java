package com.yakzhanov.flatseeker.model;

import lombok.Getter;

public enum ProcessStatus {

    UNKNOWN("Не известно", "red", true),
    NEW("Новый", "green", true),
    WEBFORM_SENT("Форма отправлена", "#228b22", true),
    REQUEST_SENT("Запрос отправлен", "cadetblue", true),
    RESPONSE_RECEIVED("Отклик получен", "orangered", true),
    MEETING_SCHEDULED("Встреча назначена", "cornflowerblue", true),
    WAITING_CONFIRMATION("Ожидает решения (мы)", "orange", true),
    WAITING_DECISION("Ожидает решения (владельцы)", "darkred", true),
    REJECTED_OWNER("Отклонено (владельцы)", "gray", false),
    REJECTED_ME("Отклонено (мы)", "gray", false),
    OUTDATED("Просрочено", "gray", false),
    DUPLICATE("Дубликат", "gray", false),
    ;

    @Getter
    private final String title;

    @Getter
    private final String color;

    @Getter
    private final boolean active;

    ProcessStatus(String title, String color, boolean active) {
        this.title = title;
        this.color = color;
        this.active = active;
    }
}
