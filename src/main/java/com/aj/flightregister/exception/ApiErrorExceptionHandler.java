package com.aj.flightregister.exception;


import com.aj.flightregister.model.nondb.ApiErrorObj;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;


@RestControllerAdvice
public class ApiErrorExceptionHandler {

    @ExceptionHandler(ItemAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    private ResponseEntity<ApiErrorObj> handleApiErrorException409(ItemAlreadyExistsException exc) {
        return error(exc);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ApiErrorObj> handle(MethodArgumentNotValidException exc) {
        var validationList = exc.getFieldErrors();

        StringBuilder errorMessage = new StringBuilder();
        for (FieldError error : validationList) {
            errorMessage.append(String.format("%s %s. ", error.getField(), error.getDefaultMessage()));
        }
        return new ResponseEntity<>(new ApiErrorObj("Validation error", errorMessage.toString()), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ItemNotFoundException.class)
    private ResponseEntity<ApiErrorObj> handleApiErrorException404(ItemNotFoundException exc) {
        return error(exc);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    private ResponseEntity<ApiErrorObj> handleApiErrorException404(NoSuchElementException exc) {
        return new ResponseEntity<>(new ApiErrorObj("Not found", exc.toString()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.LOCKED)
    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<ApiErrorObj> handleApiErrorException423(DataIntegrityViolationException exc) {
        return new ResponseEntity<>(new ApiErrorObj("The given record cannot be deleted. It is may used by others", exc.toString()), HttpStatus.LOCKED);
    }

    private ResponseEntity<ApiErrorObj> error(ApiErrorException exc) {
        return new ResponseEntity<>(new ApiErrorObj(exc.getErrorName(), exc.getErrorMessage()), exc.getHttpStatus());
    }
}
