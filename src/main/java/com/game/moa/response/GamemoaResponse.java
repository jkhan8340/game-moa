package com.game.moa.response;


import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class GamemoaResponse<T> {

    private static final String DEFAULT_SUCCESS_MESSAGE = "success";

    private final String message;
    private final int statusCode;
    private final T data;

    protected GamemoaResponse (T data, String message, int statusCode) {
        this.message = (StringUtils.isEmpty(message)) ? DEFAULT_SUCCESS_MESSAGE : message;
        this.statusCode = statusCode;
        this.data = data;
    }

    public static <T> GamemoaResponse<T> from(T data, String message, int statusCode) {
        return new GamemoaResponse<>(data, message, statusCode);
    }

}
