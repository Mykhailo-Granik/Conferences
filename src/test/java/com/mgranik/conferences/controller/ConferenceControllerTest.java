package com.mgranik.conferences.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgranik.conferences.dto.ConferenceDTO;
import com.mgranik.conferences.entity.Conference;
import com.mgranik.conferences.service.ConferenceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;

import static com.mgranik.conferences.tools.ZonedDateTimeMatcher.matchesZonedDateTime;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConferenceController.class)
class ConferenceControllerTest {

    @MockBean
    private ConferenceService conferenceService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateConference() throws Exception {

        ConferenceDTO conferenceDTO = new ConferenceDTO(
                "Test Conference",
                "java",
                ZonedDateTime.now(),
                ZonedDateTime.now().plusDays(1),
                101
        );
        Conference conference = Conference.fromDTO(conferenceDTO);

        given(conferenceService.createConference(any())).willReturn(conference);

        mockMvc.perform(post("/conferences")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conferenceDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(conference.getName()))
                .andExpect(jsonPath("$.start", matchesZonedDateTime(conference.getStart())))
                .andExpect(jsonPath("$.end", matchesZonedDateTime(conference.getEnd())))
                .andExpect(jsonPath("$.topic").value(conference.getTopic()))
                .andExpect(jsonPath("$.participantsCount").value(conference.getParticipantsCount()));

    }


}