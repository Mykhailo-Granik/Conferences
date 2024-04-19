package com.mgranik.conferences.service;

import com.mgranik.conferences.dto.ConferenceDTO;
import com.mgranik.conferences.entity.Conference;
import com.mgranik.conferences.exception.ConferenceAlreadyExistsException;
import com.mgranik.conferences.exception.ConferencesShouldNotIntersectException;
import com.mgranik.conferences.repository.ConferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;

    public Conference createConference(ConferenceDTO conferenceDTO) {
        if (conferenceRepository.existsByName(conferenceDTO.name())) {
            throw new ConferenceAlreadyExistsException();
        }
        Conference conference = Conference.fromDTO(conferenceDTO);
        if (intersectsWithExistingConferences(conference)) {
            throw new ConferencesShouldNotIntersectException();
        }
        return conferenceRepository.save(conference);
    }

    private boolean intersectsWithExistingConferences(Conference conference) {
        return StreamSupport.stream(conferenceRepository.findAll().spliterator(), false)
                .anyMatch(existingConference -> existingConference.intersects(conference));
    }

    public List<Conference> findAllConferences() {
        return StreamSupport.stream(conferenceRepository.findAll().spliterator(), false)
                .toList();
    }

}
