package com.mgranik.conferences.service;

import com.mgranik.conferences.dto.ConferenceDTO;
import com.mgranik.conferences.entity.Conference;
import com.mgranik.conferences.exception.ConferenceAlreadyExistsException;
import com.mgranik.conferences.exception.ConferencesShouldNotIntersectException;
import com.mgranik.conferences.repository.ConferenceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConferenceServiceTest {

    public static final String CONFERENCE_NAME = "Java Conference";
    @Mock
    private ConferenceRepository conferenceRepository;

    private ConferenceService underTest;

    @BeforeEach
    public void setUp() {
        underTest = new ConferenceService(conferenceRepository);
    }

    @Test
    public void whenConferenceWithSameNameExistsShouldThrowException() {
        when(conferenceRepository.existsByName(CONFERENCE_NAME)).thenReturn(true);
        assertThrows(ConferenceAlreadyExistsException.class, () ->
                underTest.createConference(
                        conference(ZonedDateTime.now().minusDays(1), ZonedDateTime.now().plusDays(1))
                )
        );
    }

    private ConferenceDTO conference(ZonedDateTime start, ZonedDateTime end) {
        return new ConferenceDTO(
                CONFERENCE_NAME,
                "Java Conference is a conference about Java",
                start,
                end,
                101
        );
    }

    @Test
    public void whenConferenceDatesIntersectShouldThrowException() {
        when(conferenceRepository.existsByName(CONFERENCE_NAME)).thenReturn(false);
        when(conferenceRepository.findAll()).thenReturn(
                List.of(
                        Conference.fromDTO(conference(ZonedDateTime.now().minusDays(2), ZonedDateTime.now().plusDays(2)))
                )
        );
        assertThrows(ConferencesShouldNotIntersectException.class, () ->
                underTest.createConference(
                        conference(ZonedDateTime.now().minusDays(1), ZonedDateTime.now().plusDays(1))
                )
        );
    }

}