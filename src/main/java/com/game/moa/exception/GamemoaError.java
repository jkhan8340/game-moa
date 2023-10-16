package com.game.moa.exception;

import lombok.Getter;

@Getter
public class GamemoaError {

    private final String message;
    private final int code;

    private GamemoaError(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public static GamemoaError from(int code, String message) {
        return new GamemoaError(message, code);
    }
}