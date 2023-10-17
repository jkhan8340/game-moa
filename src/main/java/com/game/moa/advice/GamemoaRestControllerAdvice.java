package com.game.moa.advice;

import com.game.moa.response.GamemoaErrorResponse;
import com.game.moa.exception.GamemoaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GamemoaRestControllerAdvice {

    @ExceptionHandler(GamemoaException.class)
    public <T> ResponseEntity<GamemoaErrorResponse<T>> handleException(GamemoaException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(GamemoaErrorResponse.from(e.getHttpStatus().value(), e.getMessage(), null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GamemoaErrorResponse<Map<String, String>>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(GamemoaErrorResponse.from(HttpStatus.BAD_REQUEST.value(), e.getMessage(), errors));
    }

}
