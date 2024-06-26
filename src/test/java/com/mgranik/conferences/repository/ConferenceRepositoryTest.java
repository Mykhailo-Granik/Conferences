package com.mgranik.conferences.repository;

import com.mgranik.conferences.ConferenceBuilder;
import com.mgranik.conferences.entity.Conference;
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
        Conference conference = new ConferenceBuilder("Test Conference", "java").build();
        Conference savedConference = conferenceRepository.save(conference);
        assertNotNull(savedConference);
    }

    @Test
    public void shouldFindAllConferences() {
        Conference conference1 = new ConferenceBuilder("Test Conference 1", "java").build();
        Conference conference2 = new ConferenceBuilder("Test Conference 2", "java").build();
        conferenceRepository.save(conference1);
        conferenceRepository.save(conference2);
        assertEquals(2, findAll().size());
    }

    private List<Conference> findAll() {
        return StreamSupport.stream(conferenceRepository.findAll().spliterator(), false)
                .toList();
    }

    @Test
    public void existsByNameShouldReturnTrueWhenSuchConferenceExists() {
        Conference conference = new ConferenceBuilder("Test Conference", "java").build();
        conferenceRepository.save(conference);
        assertTrue(conferenceRepository.existsByName("Test Conference"));
    }

    @Test
    public void existsByNameShouldReturnFalseWhenSuchConferenceDoesNotExist() {
        Conference conference = new ConferenceBuilder("Test Conference", "java").build();
        conferenceRepository.save(conference);
        assertFalse(conferenceRepository.existsByName("Random Conference"));
    }

    @Test
    public void shouldFindConferenceById() {
        Conference conference = new ConferenceBuilder("Test Conference", "java").build();
        Conference savedConference = conferenceRepository.save(conference);
        assertTrue(conferenceRepository.findById(savedConference.getId()).isPresent());
    }

    @Test
    public void shouldNotFindConferenceByIdIfItDoesNotExist() {
        assertFalse(conferenceRepository.findById(1).isPresent());
    }

}