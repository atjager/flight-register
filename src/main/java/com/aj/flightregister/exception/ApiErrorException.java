package com.aj.flightregister.exception;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@Slf4j
public abstract class ApiErrorException extends Exception {

    private String errorName;

    private String errorMessage;

    public ApiErrorException(String name, String msg) {
        super(msg);
        this.errorMessage = msg;
        this.errorName = name;
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
        log.error(errorMessage);
    }

    public abstract HttpStatus getHttpStatus();
}
