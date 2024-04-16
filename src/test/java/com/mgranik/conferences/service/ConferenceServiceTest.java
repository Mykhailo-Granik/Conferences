package com.mgranik.conferences.service;

import com.mgranik.conferences.dto.ConferenceDTO;
import com.mgranik.conferences.exception.ConferenceAlreadyExistsException;
import com.mgranik.conferences.repository.ConferenceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConferenceServiceTest {

    public static final String CONFERENCE_NAME = "Java Conference";
    @Mock
    private ConferenceRepository conferenceRepository;

    @Test
    public void whenConferenceWithSameNameExistsShouldThrowException() {
        when(conferenceRepository.existsByName(CONFERENCE_NAME)).thenReturn(true);
        ConferenceService conferenceService = new ConferenceService(conferenceRepository);
        assertThrows(ConferenceAlreadyExistsException.class, () -> conferenceService.createConference(conference()));
    }

    private ConferenceDTO conference() {
        return new ConferenceDTO(
                CONFERENCE_NAME,
                "Java Conference is a conference about Java",
                ZonedDateTime.now().minusDays(1),
                ZonedDateTime.now().plusDays(1),
                12
        );
    }

}