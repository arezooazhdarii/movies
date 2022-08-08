package com.backbase.movies.exception;

import ch.qos.logback.core.status.Status;
import lombok.Getter;


@Getter
public class BaseException extends RuntimeException {

    private final String title;
    private final String message;
    private final Status status;
    private final Object[] messageArgs;

    public BaseException(String title, String message, Status status) {
        this(title, message, status, null);
    }

    public BaseException(String title, String message, Status status, Object[] messageArgs) {
        this.title = title;
        this.message = message;
        this.status = status;
        this.messageArgs = messageArgs;
    }
}
