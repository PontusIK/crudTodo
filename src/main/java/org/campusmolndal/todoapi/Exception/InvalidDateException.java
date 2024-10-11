package org.campusmolndal.todoapi.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidDateException extends RuntimeException {
    public InvalidDateException(String msg) {
        super(msg);
    }
}
