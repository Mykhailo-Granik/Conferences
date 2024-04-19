package com.mgranik.conferences.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Conference with given id not found")
public class ConferenceNotFoundException extends RuntimeException {

    public ConferenceNotFoundException(Integer id) {
        super(STR."Conference with id \{id} not found");
    }

}
