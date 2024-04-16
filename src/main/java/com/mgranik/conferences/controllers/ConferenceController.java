package com.mgranik.conferences.controllers;

import com.mgranik.conferences.data.Conference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conferences")
public class ConferenceController {

    @PostMapping
    public String createConference(@RequestBody Conference conference) {
        System.out.println(conference);
        return "Conference created";
    }

}
