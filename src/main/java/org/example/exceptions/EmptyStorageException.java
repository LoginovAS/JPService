package org.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ACCEPTED)
public class EmptyStorageException extends RuntimeException {

    public EmptyStorageException(String message) {
        super(message);
    }

}
