package com.mgranik.conferences.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class PersistenceAdapterTest {

    @Mock
    private ConferenceRepository conferenceRepository;

    @Test
    public void shouldReturnTrueWhenConferenceExists() {
        PersistenceAdapter persistenceAdapter = new PersistenceAdapter(conferenceRepository);
        lenient().when(conferenceRepository.existsByName("Test Conference")).thenReturn(true);
        assertTrue(persistenceAdapter.existsByName("Test Conference"));
    }

    @Test
    public void shouldReturnFalseWhenConferenceDoesNotExist() {
        PersistenceAdapter persistenceAdapter = new PersistenceAdapter(conferenceRepository);
        lenient().when(conferenceRepository.existsByName("Test Conference")).thenReturn(false);
        assertFalse(persistenceAdapter.existsByName("Test Conference"));
    }

}