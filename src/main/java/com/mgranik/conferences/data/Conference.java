package com.mgranik.conferences.data;

import java.time.ZonedDateTime;

public record Conference(String name, String topic, ZonedDateTime start, ZonedDateTime end, int participantsCount) {

}
