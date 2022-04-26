package org.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public class BehaviorException extends Exception {

    public BehaviorException(String message) {
        super(message);
    }

}
