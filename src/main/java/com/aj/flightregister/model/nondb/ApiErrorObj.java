package com.aj.flightregister.model.nondb;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorObj {

    private String
            errorName,
            errorMessage;

}
