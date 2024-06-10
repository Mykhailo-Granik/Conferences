package com.mgranik.conferences;

import com.mgranik.conferences.entity.Conference;

import java.time.ZonedDateTime;

public class ConferenceBuilder {

    private final Conference conference;

    public ConferenceBuilder(String name, String topic) {
        conference = new Conference();
        conference.setName(name);
        conference.setTopic(topic);
        conference.setStartDateTime(ZonedDateTime.now());
        conference.setEndDateTime(ZonedDateTime.now().plusDays(1));
        conference.setParticipantsCount(101);
    }

    public Conference build() {
        return conference;
    }

}
