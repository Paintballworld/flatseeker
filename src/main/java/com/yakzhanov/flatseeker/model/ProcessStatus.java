package com.yakzhanov.flatseeker.model;

import lombok.Getter;

public enum ProcessStatus {

    NEW("Новый"),
    WEBFORM_SENT("Форма отправлена"),
    REQUEST_SENT("Запрос отправлен"),
    RESPONSE_RECEIVED("Отклик получен"),
    MEETING_SCHEDULED("Встреча назначена"),
    WAITING_CONFIRMATION("Ожидает решения (мы)"),
    WAITING_DECISION("Ожидает решения (владельцы)"),
    REJECTED_OWNER("Отклонено (мы)"),
    REJECTED_ME("Отклонено (владельцы)"),
    OUTDATED("Просрочено"),
    UNKNOWN("Не известно")
    ;

    @Getter
    private final String title;

    ProcessStatus(String title) {
        this.title = title;
    }
}
