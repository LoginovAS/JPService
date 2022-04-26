package org.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Can be thrown when request arguments are invalid.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadArgumentException extends IllegalArgumentException{

    public BadArgumentException(String message) {
        super(message);
    }

}
