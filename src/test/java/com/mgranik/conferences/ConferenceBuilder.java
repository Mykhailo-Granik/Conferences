package com.mgranik.conferences;

import com.mgranik.conferences.entity.ConferenceEntity;

import java.time.ZonedDateTime;

public class ConferenceBuilder {

    private final ConferenceEntity conference;

    public ConferenceBuilder(String name, String topic) {
        conference = new ConferenceEntity();
        conference.setName(name);
        conference.setTopic(topic);
        conference.setStartDateTime(ZonedDateTime.now());
        conference.setEndDateTime(ZonedDateTime.now().plusDays(1));
        conference.setParticipantsCount(101);
    }

    public ConferenceEntity build() {
        return conference;
    }

}
