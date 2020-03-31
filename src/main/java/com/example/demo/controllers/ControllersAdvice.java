package com.example.demo.controllers;

import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.models.ErrorResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllersAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException exc) {
        return buildResponse(exc, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ErrorResponse> buildResponse(Exception exc, HttpStatus httpStatus) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestomp(System.currentTimeMillis());

        if (exc instanceof EmptyResultDataAccessException) {
            errorResponse.setMessage("Not Found!");
            return new ResponseEntity<>(errorResponse, httpStatus);
        }
        errorResponse.setMessage(exc.getMessage());

        return new ResponseEntity<>(errorResponse, httpStatus);

    }

}
