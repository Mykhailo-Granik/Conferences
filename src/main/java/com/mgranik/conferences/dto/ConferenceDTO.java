package com.mgranik.conferences.dto;

import java.time.ZonedDateTime;

public record ConferenceDTO(String name, String topic, ZonedDateTime start, ZonedDateTime end, int participantsCount) {

}
