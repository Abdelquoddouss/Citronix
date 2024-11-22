package com.java.citronix.service.Impl;

import com.java.citronix.domaine.entities.Recolte;
import com.java.citronix.exception.ResourceNotFoundException;
import com.java.citronix.repository.RecolteRepository;
import com.java.citronix.service.RecolteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RecolteServiceImpl implements RecolteService {

    @Autowired
    private RecolteRepository recolteRepository;

    @Override
    public Recolte createRecolte(Recolte recolte) {
        return recolteRepository.save(recolte);
    }

    @Override
    public Recolte getRecolteById(UUID recolteId) {
        return recolteRepository.findById(recolteId)
                .orElseThrow(() -> new ResourceNotFoundException("Recolte not found with ID: " + recolteId));
    }

}
