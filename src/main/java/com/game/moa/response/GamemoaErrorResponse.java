package com.game.moa.response;

public final class GamemoaErrorResponse<T> extends GamemoaResponse<T> {

    private GamemoaErrorResponse(String message, int code, T errorData) {
        super(errorData, message, code);
    }

    public static <T> GamemoaErrorResponse<T> from(int code, String message, T data) {
        return new GamemoaErrorResponse<>(message, code, data);
    }

}