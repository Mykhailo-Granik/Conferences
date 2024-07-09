package com.mgranik.conferences.core.entity;

import java.time.ZonedDateTime;

public record Conference(

        String name,
        String topic,
        ZonedDateTime start,
        ZonedDateTime end,
        int participantsCount
) {

}
