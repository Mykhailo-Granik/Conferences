package com.mgranik.conferences.service;

import com.mgranik.conferences.dto.ConferenceDTO;
import com.mgranik.conferences.entity.Conference;
import com.mgranik.conferences.exception.ConferenceAlreadyExistsException;
import com.mgranik.conferences.repository.ConferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;

    public Conference createConference(ConferenceDTO conference) {
        if (conferenceRepository.existsByName(conference.name())) {
            throw new ConferenceAlreadyExistsException();
        }
        return conferenceRepository.save(Conference.fromDTO(conference));
    }

}
