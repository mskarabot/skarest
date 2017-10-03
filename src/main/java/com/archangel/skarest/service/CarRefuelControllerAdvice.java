package com.archangel.skarest.service;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class CarRefuelControllerAdvice {

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    VndErrors runtimeExceptionHandler(RuntimeException ex) {
        return new VndErrors("error", ex.getMessage());
    }
}
