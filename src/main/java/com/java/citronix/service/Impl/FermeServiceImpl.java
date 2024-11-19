package com.java.citronix.service.Impl;


import com.java.citronix.domaine.entities.Ferme;
import com.java.citronix.exception.FermeNotFoundException;
import com.java.citronix.exception.ResourceNotFoundException;
import com.java.citronix.repository.FermeRepository;
import com.java.citronix.service.FermeService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

@Service("fermeService")
@RequiredArgsConstructor
public class FermeServiceImpl implements FermeService {


    @Autowired
    private FermeRepository fermeRepository;

    @Override
    public Ferme createFerme(Ferme ferme) {
        return fermeRepository.save(ferme);
    }

    @Override
    public Ferme updateFerme(UUID id, Ferme fermeDetails) {
        Ferme existingFerme = fermeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ferme with ID " + id + " not found"));

        existingFerme.setNom(fermeDetails.getNom());
        existingFerme.setLocalisation(fermeDetails.getLocalisation());
        existingFerme.setSuperficie(fermeDetails.getSuperficie());
        existingFerme.setDateCreation(fermeDetails.getDateCreation());

        return fermeRepository.save(existingFerme);
    }

    @Override
    public Ferme getFermeById(UUID id) {
        return fermeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ferme with ID " + id + " not found"));
    }


    @Override
    public List<Ferme> getAllFermes() {
        return fermeRepository.findAll();
    }



    @Override
    public void deleteFerme(UUID id) {
        Ferme ferme = fermeRepository.findById(id)
                .orElseThrow(() -> new FermeNotFoundException("Ferme not found with id: " + id));
        fermeRepository.delete(ferme);
    }





}
