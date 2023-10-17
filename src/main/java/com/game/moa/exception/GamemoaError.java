package com.game.moa.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GamemoaError<T> {

    private final String message;
    private final int code;

    @JsonProperty("error_data")
    private final T errorData;

    private GamemoaError(String message, int code, T errorData) {
        this.message = message;
        this.code = code;
        this.errorData = errorData;
    }

    public static <T> GamemoaError<T> from(int code, String message, T data) {
        return new GamemoaError<>(message, code, data);
    }
}