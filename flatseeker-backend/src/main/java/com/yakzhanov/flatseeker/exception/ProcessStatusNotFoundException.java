package com.yakzhanov.flatseeker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Не найден статус")
public class ProcessStatusNotFoundException extends RuntimeException {
}
