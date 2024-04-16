package com.mgranik.conferences.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Conference with such name already exists")
public class ConferenceAlreadyExistsException extends RuntimeException {

    public ConferenceAlreadyExistsException() {
        super();
    }
}
