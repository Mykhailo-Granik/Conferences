package com.mgranik.conferences.persistence;

import com.mgranik.conferences.ConferenceBuilder;
import com.mgranik.conferences.entity.ConferenceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class ConferenceRepositoryTest {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Test
    public void shouldSaveAConference() {
        ConferenceEntity conference = new ConferenceBuilder("Test Conference", "java").build();
        ConferenceEntity savedConference = conferenceRepository.save(conference);
        assertNotNull(savedConference);
    }

    @Test
    public void shouldFindAllConferences() {
        ConferenceEntity conference1 = new ConferenceBuilder("Test Conference 1", "java").build();
        ConferenceEntity conference2 = new ConferenceBuilder("Test Conference 2", "java").build();
        conferenceRepository.save(conference1);
        conferenceRepository.save(conference2);
        assertEquals(2, findAll().size());
    }

    private List<ConferenceEntity> findAll() {
        return StreamSupport.stream(conferenceRepository.findAll().spliterator(), false)
                .toList();
    }

    @Test
    public void existsByNameShouldReturnTrueWhenSuchConferenceExists() {
        ConferenceEntity conference = new ConferenceBuilder("Test Conference", "java").build();
        conferenceRepository.save(conference);
        assertTrue(conferenceRepository.existsByName("Test Conference"));
    }

    @Test
    public void existsByNameShouldReturnFalseWhenSuchConferenceDoesNotExist() {
        ConferenceEntity conference = new ConferenceBuilder("Test Conference", "java").build();
        conferenceRepository.save(conference);
        assertFalse(conferenceRepository.existsByName("Random Conference"));
    }

    @Test
    public void shouldFindConferenceById() {
        ConferenceEntity conference = new ConferenceBuilder("Test Conference", "java").build();
        ConferenceEntity savedConference = conferenceRepository.save(conference);
        assertTrue(conferenceRepository.findById(savedConference.getId()).isPresent());
    }

    @Test
    public void shouldNotFindConferenceByIdIfItDoesNotExist() {
        assertFalse(conferenceRepository.findById(1).isPresent());
    }

    @Test
    public void shouldFindAllConferencesExceptWithSpecifiedIds() {
        ConferenceEntity conference1 = new ConferenceBuilder("Test Conference 1", "java").build();
        ConferenceEntity conference2 = new ConferenceBuilder("Test Conference 2", "java").build();
        ConferenceEntity savedConference1 = conferenceRepository.save(conference1);
        ConferenceEntity savedConference2 = conferenceRepository.save(conference2);
        List<ConferenceEntity> conferences = conferenceRepository.findByIdNot(savedConference1.getId());
        assertEquals(1, conferences.size());
        assertEquals(savedConference2.getId(), conferences.get(0).getId());
    }

}