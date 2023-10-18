package com.game.moa.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class GamemoaResponse<T> {

    private static final String DEFAULT_SUCCESS_MESSAGE = "success";

    @JsonProperty("message")
    private final String message;

    @JsonProperty("status_code")
    private final int statusCode;

    @JsonProperty("data")
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
