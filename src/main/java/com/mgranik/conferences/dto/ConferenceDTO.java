package com.mgranik.conferences.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

public record ConferenceDTO(
        @NotBlank String name,
        @NotBlank String topic,
        @NotNull ZonedDateTime start,
        @NotNull ZonedDateTime end,
        @Min(101) int participantsCount) {

}
