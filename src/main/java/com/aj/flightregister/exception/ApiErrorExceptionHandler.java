package com.aj.flightregister.exception;


import com.aj.flightregister.model.nondb.ApiErrorObj;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ApiErrorExceptionHandler {

    @ExceptionHandler(ItemAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiErrorObj> handleApiErrorException409(ItemAlreadyExistsException exc) {
        return error(exc);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorObj> handle(MethodArgumentNotValidException exc) {
        var validationList = exc.getFieldErrors();

        StringBuilder errorMessage = new StringBuilder();
        for (FieldError error : validationList) {
            errorMessage.append(String.format("%s %s. ", error.getField(), error.getDefaultMessage()));
        }
        return new ResponseEntity<>(new ApiErrorObj("validationError", errorMessage.toString()), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ApiErrorObj> handleApiErrorException404(ItemNotFoundException exc) {
        return error(exc);
    }

    private ResponseEntity<ApiErrorObj> error(ApiErrorException exc) {
        return new ResponseEntity<>(new ApiErrorObj(exc.getErrorName(), exc.getErrorMessage()), exc.getHttpStatus());
    }
}