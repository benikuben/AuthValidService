package ru.neoflex.authvalidservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.neoflex.authvalidservice.dtos.ErrorResponse;
import ru.neoflex.authvalidservice.exceptions.AuthException;
import ru.neoflex.authvalidservice.exceptions.NotFoundException;

@ControllerAdvice
@Slf4j
public class ErrorHandlingControllerAdvice {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNotFoundException(NotFoundException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        log.warn("NotFoundException {}", response);
        return response;
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleAuthException(AuthException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        log.warn("AuthException {}", response);
        return response;
    }
}
