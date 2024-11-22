package com.java.citronix.service;

import com.java.citronix.domaine.entities.Champ;
import com.java.citronix.domaine.entities.Ferme;
import com.java.citronix.exception.ResourceNotFoundException;
import com.java.citronix.exception.SuperficieValidationException;
import com.java.citronix.repository.ChampRepository;
import com.java.citronix.repository.FermeRepository;
import com.java.citronix.service.Impl.ChampServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChampServiceImplTest {

    @Mock
    private ChampRepository champRepository;

    @Mock
    private FermeRepository fermeRepository;

    @InjectMocks
    private ChampServiceImpl champService;

    private Ferme ferme;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ferme = new Ferme();
        ferme.setId(UUID.randomUUID());
        ferme.setSuperficie(100.0);
        ferme.setChamps(new ArrayList<>());
    }

    @Test
    void saveChamp_succeeds_whenValidChamp() {
        // Given
        Champ champ = new Champ();
        champ.setSuperficie(10.0);

        when(fermeRepository.findById(ferme.getId())).thenReturn(Optional.of(ferme));
        when(champRepository.save(champ)).thenReturn(champ);

        // When
        Champ savedChamp = champService.save(champ, ferme.getId());

        // Then
        assertNotNull(savedChamp);
        verify(champRepository).save(champ);
    }

    @Test
    void saveChamp_throwsResourceNotFoundException_whenFermeNotFound() {
        // Given
        Champ champ = new Champ();
        champ.setSuperficie(10.0);

        when(fermeRepository.findById(ferme.getId())).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ResourceNotFoundException.class, () -> champService.save(champ, ferme.getId()));
        verify(champRepository, never()).save(champ);
    }

    @Test
    void saveChamp_throwsSuperficieValidationException_whenSuperficieTooSmall() {
        // Given
        Champ champ = new Champ();
        champ.setSuperficie(0.05);

        when(fermeRepository.findById(ferme.getId())).thenReturn(Optional.of(ferme));

        // When & Then
        assertThrows(SuperficieValidationException.class, () -> champService.save(champ, ferme.getId()));
        verify(champRepository, never()).save(champ);
    }

    @Test
    void saveChamp_throwsSuperficieValidationException_whenSuperficieExceedsFarmSuperficie() {
        // Given
        Champ champ = new Champ();
        champ.setSuperficie(60.0);

        when(fermeRepository.findById(ferme.getId())).thenReturn(Optional.of(ferme));

        // When & Then
        assertThrows(SuperficieValidationException.class, () -> champService.save(champ, ferme.getId()));
        verify(champRepository, never()).save(champ);
    }

    @Test
    void saveChamp_throwsSuperficieValidationException_whenTotalSuperficieExceedsFarmSuperficie() {
        // Given
        Champ champ = new Champ();
        champ.setSuperficie(20.0);

        Champ existingChamp = new Champ();
        existingChamp.setSuperficie(85.0);
        ferme.getChamps().add(existingChamp);

        when(fermeRepository.findById(ferme.getId())).thenReturn(Optional.of(ferme));

        // When & Then
        assertThrows(SuperficieValidationException.class, () -> champService.save(champ, ferme.getId()));
        verify(champRepository, never()).save(champ);
    }

    @Test
    void saveChamp_throwsSuperficieValidationException_whenFarmHasMoreThan10Fields() {
        // Given
        for (int i = 0; i < 10; i++) {
            ferme.getChamps().add(new Champ());
        }

        Champ champ = new Champ();
        champ.setSuperficie(5.0);

        when(fermeRepository.findById(ferme.getId())).thenReturn(Optional.of(ferme));

        // When & Then
        assertThrows(SuperficieValidationException.class, () -> champService.save(champ, ferme.getId()));
        verify(champRepository, never()).save(champ);
    }
}
