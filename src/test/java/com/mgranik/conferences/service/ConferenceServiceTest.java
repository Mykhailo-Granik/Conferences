package com.mgranik.conferences.service;

import com.mgranik.conferences.core.persistence.PersistencePort;
import com.mgranik.conferences.dto.ConferenceDTO;
import com.mgranik.conferences.entity.ConferenceEntity;
import com.mgranik.conferences.exception.ConferenceAlreadyExistsException;
import com.mgranik.conferences.exception.ConferenceNotFoundException;
import com.mgranik.conferences.exception.ConferencesShouldNotIntersectException;
import com.mgranik.conferences.persistence.ConferenceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConferenceServiceTest {

    public static final String CONFERENCE_NAME = "Java Conference";
    @Mock
    private ConferenceRepository conferenceRepository;
    @Mock
    private PersistencePort persistencePort;

    private ConferenceService underTest;

    @BeforeEach
    public void setUp() {
        underTest = new ConferenceService(conferenceRepository, persistencePort);
    }

    @Test
    public void whenConferenceWithSameNameExistsShouldThrowException() {
        when(persistencePort.existsByName(CONFERENCE_NAME)).thenReturn(true);
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
        when(persistencePort.existsByName(CONFERENCE_NAME)).thenReturn(false);
        when(conferenceRepository.findAll()).thenReturn(
                List.of(
                        ConferenceEntity.fromDTO(conference(ZonedDateTime.now().minusDays(2), ZonedDateTime.now().plusDays(2)))
                )
        );
        assertThrows(ConferencesShouldNotIntersectException.class, () ->
                underTest.createConference(
                        conference(ZonedDateTime.now().minusDays(1), ZonedDateTime.now().plusDays(1))
                )
        );
    }

    @Test
    public void whenBusinessLogicRulesAreMetShouldCreateConference() {
        when(persistencePort.existsByName(CONFERENCE_NAME)).thenReturn(false);
        when(conferenceRepository.findAll()).thenReturn(emptyList());
        ConferenceDTO conference = conference(ZonedDateTime.now().plusDays(1), ZonedDateTime.now().plusDays(2));
        underTest.createConference(conference);
        verify(conferenceRepository).save(any());
    }

    @Test
    public void shouldFindAllConferences() {
        underTest.findAllConferences();
        verify(conferenceRepository).findAll();
    }

    @Test
    public void whenConferenceIsNotFoundAndUpdateIsCalledShouldThrowException() {
        when(conferenceRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ConferenceNotFoundException.class, () ->
                underTest.updateConference(
                        conference(ZonedDateTime.now().plusDays(1), ZonedDateTime.now().plusDays(2)),
                        1
                )
        );
    }

    @Test
    public void whenConferenceDatesIntersectOnUpdateShouldThrowException() {
        ConferenceDTO conferenceToUpdate = conference(ZonedDateTime.now().plusDays(1), ZonedDateTime.now().plusDays(2));
        ConferenceDTO otherConference = conference(ZonedDateTime.now().plusDays(3), ZonedDateTime.now().plusDays(4));
        when(conferenceRepository.findById(1)).thenReturn(Optional.of(
                ConferenceEntity.fromDTO(conferenceToUpdate)
        ));
        when(conferenceRepository.findAll()).thenReturn(
                List.of(
                        ConferenceEntity.fromDTO(conferenceToUpdate, 1),
                        ConferenceEntity.fromDTO(otherConference, 2)
                )
        );
        assertThrows(ConferencesShouldNotIntersectException.class, () ->
                underTest.updateConference(
                        conference(ZonedDateTime.now().plusDays(1), ZonedDateTime.now().plusDays(5)),
                        1
                )
        );
    }

    @Test
    public void whenBusinessLogicRulesAreMetShouldUpdateConference() {
        ConferenceDTO conferenceToUpdate = conference(ZonedDateTime.now().plusDays(1), ZonedDateTime.now().plusDays(2));
        when(conferenceRepository.findById(1)).thenReturn(Optional.of(
                ConferenceEntity.fromDTO(conferenceToUpdate)
        ));
        when(conferenceRepository.findAll()).thenReturn(emptyList());
        underTest.updateConference(
                conference(ZonedDateTime.now().plusDays(1), ZonedDateTime.now().plusDays(5)),
                1
        );
        verify(conferenceRepository).save(any());
    }

}