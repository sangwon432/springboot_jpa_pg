package com.sangwon.springboot_jpa_pg.exceptions;

import org.springframework.http.HttpStatus;

public class UserApiException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public UserApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public UserApiException(HttpStatus status, String message, String message1) {
        super(message);

        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
