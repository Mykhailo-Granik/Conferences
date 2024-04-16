package com.mgranik.conferences.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Conference dates should not intersect")
public class ConferencesShouldNotIntersectException extends RuntimeException {
}
