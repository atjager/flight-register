package com.aj.flightregister.exception;

import org.springframework.http.HttpStatus;

public class ItemAlreadyExistsException extends ApiErrorException {

    public ItemAlreadyExistsException(String string, String valueOf) {
        super(string, valueOf);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
