package com.java.citronix.service.Impl;

import com.java.citronix.domaine.entities.Vente;
import com.java.citronix.exception.ResourceNotFoundException;
import com.java.citronix.repository.VenteRepository;
import com.java.citronix.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VenteServiceImpl implements VenteService {

    @Autowired
    private VenteRepository venteRepository;

    @Override
    public Vente createVente(Vente vente) {
        return venteRepository.save(vente);
    }

    @Override
    public Vente getVenteById(UUID venteId) {
        return venteRepository.findById(venteId)
                .orElseThrow(() -> new ResourceNotFoundException("Vente not found with ID: " + venteId));
    }

    @Override
    public List<Vente> getAllVentes() {
        return venteRepository.findAll();
    }

    @Override
    public void deleteVente(UUID venteId) {
        Vente vente = venteRepository.findById(venteId)
                .orElseThrow(() -> new ResourceNotFoundException("Vente not found with ID: " + venteId));
        venteRepository.delete(vente);
    }

    public Double calculerRevenu(UUID venteId) {
        Vente vente = getVenteById(venteId);
        return vente.calculerRevenu();
    }

}
