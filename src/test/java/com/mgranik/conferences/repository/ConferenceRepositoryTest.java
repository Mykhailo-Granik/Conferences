package com.mgranik.conferences.repository;

import com.mgranik.conferences.entity.Conference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest(showSql = true)
@ActiveProfiles("test")
class ConferenceRepositoryTest {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Test
    public void shouldSaveAConference() {
        Conference conference = new Conference();
        conference.setName("Test Conference");
        conference.setTopic("java");
        conference.setStartDateTime(ZonedDateTime.now());
        conference.setEndDateTime(ZonedDateTime.now().plusDays(1));
        conference.setParticipantsCount(101);
        Conference savedConference = conferenceRepository.save(conference);
        assertNotNull(savedConference);
    }

}