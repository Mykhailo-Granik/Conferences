package com.mgranik.conferences.persistence;

import com.mgranik.conferences.core.persistence.PersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersistenceAdapter implements PersistencePort {

    private final ConferenceRepository conferenceRepository;

    @Override
    public boolean existsByName(String name) {
        return conferenceRepository.existsByName(name);
    }
}
