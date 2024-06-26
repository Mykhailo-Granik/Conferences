package com.mgranik.conferences.entity;


import com.mgranik.conferences.dto.ConferenceDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity(name = "conference")
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;
    private String topic;
    private ZonedDateTime startDateTime;
    private ZonedDateTime endDateTime;
    private int participantsCount;

    public static Conference fromDTO(ConferenceDTO conferenceDTO) {
        Conference conference = new Conference();
        conference.setName(conferenceDTO.name());
        conference.setTopic(conferenceDTO.topic());
        conference.setStartDateTime(conferenceDTO.start());
        conference.setEndDateTime(conferenceDTO.end());
        conference.setParticipantsCount(conferenceDTO.participantsCount());
        return conference;
    }

    public static Conference fromDTO(ConferenceDTO conferenceDTO, Integer id) {
        Conference conference = fromDTO(conferenceDTO);
        conference.setId(id);
        return conference;
    }

    public boolean intersects(Conference other) {
        long leftIntersection = Math.min(this.endDateTime.toEpochSecond(), other.endDateTime.toEpochSecond());
        long rightIntersection = Math.max(this.startDateTime.toEpochSecond(), other.startDateTime.toEpochSecond());
        return leftIntersection > rightIntersection;
    }
}
