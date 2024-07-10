package com.mgranik.conferences.web;

import com.mgranik.conferences.dto.ConferenceDTO;
import com.mgranik.conferences.entity.ConferenceEntity;
import com.mgranik.conferences.service.ConferenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conferences")
@RequiredArgsConstructor
public class ConferenceController {

    private final ConferenceService conferenceService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ConferenceEntity createConference(@RequestBody @Valid ConferenceDTO conference) {
        return conferenceService.createConference(conference);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ConferenceEntity> findAllConferences() {
        return conferenceService.findAllConferences();
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ConferenceEntity updateConference(@RequestBody @Valid ConferenceDTO conference, @PathVariable Integer id) {
        return conferenceService.updateConference(conference, id);
    }

}
