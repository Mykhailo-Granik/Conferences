package com.mgranik.conferences.controllers;

import com.mgranik.conferences.dto.ConferenceDTO;
import com.mgranik.conferences.entity.Conference;
import com.mgranik.conferences.repository.ConferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conferences")
@RequiredArgsConstructor
public class ConferenceController {

    private final ConferenceRepository conferenceRepository;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Conference createConference(@RequestBody ConferenceDTO conference) {
        return conferenceRepository.save(Conference.fromDTO(conference));
    }

}
