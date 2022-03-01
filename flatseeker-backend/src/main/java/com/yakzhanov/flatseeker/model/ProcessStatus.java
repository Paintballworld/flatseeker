package com.yakzhanov.flatseeker.model;

import lombok.Getter;

public enum ProcessStatus {

    UNKNOWN("Не известно", "red"),
    NEW("Новый", "green"),
    WEBFORM_SENT("Форма отправлена", "#228b22"),
    REQUEST_SENT("Запрос отправлен", "cadetblue"),
    RESPONSE_RECEIVED("Отклик получен", "orangered"),
    MEETING_SCHEDULED("Встреча назначена", "cornflowerblue"),
    WAITING_CONFIRMATION("Ожидает решения (мы)", "orange"),
    WAITING_DECISION("Ожидает решения (владельцы)", "darkred"),
    REJECTED_OWNER("Отклонено (мы)", "gray"),
    REJECTED_ME("Отклонено (владельцы)", "gray"),
    OUTDATED("Просрочено", "gray"),
    DUPLICATE("Дубликат", "gray"),
    ;

    @Getter
    private final String title;

    @Getter
    private final String color;

    ProcessStatus(String title, String color) {
        this.title = title;
        this.color = color;
    }
}
