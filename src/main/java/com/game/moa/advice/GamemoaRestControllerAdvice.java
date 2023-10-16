package com.game.moa.advice;

import com.game.moa.exception.GamemoaError;
import com.game.moa.exception.GamemoaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GamemoaRestControllerAdvice {

    @ExceptionHandler(GamemoaException.class)
    public ResponseEntity<GamemoaError> handleException(GamemoaException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(GamemoaError.from(e.getHttpStatus().value(), e.getMessage()));
    }

}
