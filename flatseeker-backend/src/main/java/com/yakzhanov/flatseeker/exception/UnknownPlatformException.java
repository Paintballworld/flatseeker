package com.yakzhanov.flatseeker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED, reason = "Данная платформа не поддерживается приложением")
public class UnknownPlatformException extends RuntimeException {
}
