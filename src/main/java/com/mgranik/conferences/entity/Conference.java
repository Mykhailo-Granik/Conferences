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
    private ZonedDateTime start;
    private ZonedDateTime end;
    private int participantsCount;

    public static Conference fromDTO(ConferenceDTO conferenceDTO) {
        Conference conference = new Conference();
        conference.setName(conferenceDTO.name());
        conference.setTopic(conferenceDTO.topic());
        conference.setStart(conferenceDTO.start());
        conference.setEnd(conferenceDTO.end());
        conference.setParticipantsCount(conferenceDTO.participantsCount());
        return conference;
    }

    public boolean intersects(Conference other) {
        long leftIntersection = Math.min(this.end.toEpochSecond(), other.end.toEpochSecond());
        long rightIntersection = Math.max(this.start.toEpochSecond(), other.start.toEpochSecond());
        return leftIntersection > rightIntersection;
    }
}
