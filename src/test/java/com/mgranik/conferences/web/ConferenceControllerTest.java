package com.mgranik.conferences.web;

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
import java.util.List;

import static com.mgranik.conferences.tools.ZonedDateTimeMatcher.matchesZonedDateTime;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

        Conference conference = Conference.fromDTO(conferenceDTO());

        given(conferenceService.createConference(any())).willReturn(conference);

        mockMvc.perform(post("/conferences")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conferenceDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(conference.getName()))
                .andExpect(jsonPath("$.start", matchesZonedDateTime(conference.getStartDateTime())))
                .andExpect(jsonPath("$.end", matchesZonedDateTime(conference.getEndDateTime())))
                .andExpect(jsonPath("$.topic").value(conference.getTopic()))
                .andExpect(jsonPath("$.participantsCount").value(conference.getParticipantsCount()));

    }

    private ConferenceDTO conferenceDTO() {
        return new ConferenceDTO(
                "Test Conference",
                "java",
                ZonedDateTime.now(),
                ZonedDateTime.now().plusDays(1),
                101
        );
    }

    @Test
    public void shouldReturnAListOfConferences() throws Exception {
        List<Conference> conferences = List.of(Conference.fromDTO(conferenceDTO()));
        given(conferenceService.findAllConferences()).willReturn(conferences);
        mockMvc.perform(get("/conferences"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(conferences.get(0).getName()))
                .andExpect(jsonPath("$[0].start", matchesZonedDateTime(conferences.get(0).getStartDateTime())))
                .andExpect(jsonPath("$[0].end", matchesZonedDateTime(conferences.get(0).getEndDateTime())))
                .andExpect(jsonPath("$[0].topic").value(conferences.get(0).getTopic()))
                .andExpect(jsonPath("$[0].participantsCount").value(conferences.get(0).getParticipantsCount()));

    }

    @Test
    public void shouldUpdateAConference() throws Exception {
        Conference conference = Conference.fromDTO(conferenceDTO());
        given(conferenceService.updateConference(any(), any())).willReturn(conference);
        mockMvc.perform(put("/conferences/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conferenceDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(conference.getName()))
                .andExpect(jsonPath("$.start", matchesZonedDateTime(conference.getStartDateTime())))
                .andExpect(jsonPath("$.end", matchesZonedDateTime(conference.getEndDateTime())))
                .andExpect(jsonPath("$.topic").value(conference.getTopic()))
                .andExpect(jsonPath("$.participantsCount").value(conference.getParticipantsCount()));
    }

}