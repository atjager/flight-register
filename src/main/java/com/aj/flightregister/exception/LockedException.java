package com.aj.flightregister.exception;

import org.springframework.http.HttpStatus;

public class LockedException extends ApiErrorException {


    public LockedException(String string, String valueOf) {
        super(string, valueOf);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.LOCKED;
    }
}

