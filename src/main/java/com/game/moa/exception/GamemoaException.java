package com.game.moa.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GamemoaException extends RuntimeException {
    private final HttpStatus httpStatus;

    public GamemoaException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
