package com.java.citronix.service.Impl;


import com.java.citronix.domaine.entities.Ferme;
import com.java.citronix.exception.FermeNotFoundException;
import com.java.citronix.exception.InvalidSearchCriteriaException;
import com.java.citronix.exception.NoResultsFoundException;
import com.java.citronix.exception.ResourceNotFoundException;
import com.java.citronix.repository.FermeRepository;
import com.java.citronix.repository.FermeSearchRepository;
import com.java.citronix.service.FermeService;
import com.java.citronix.web.vm.FermeSearchResultVm;
import com.java.citronix.web.vm.FermeSearchVm;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class FermeServiceImpl implements FermeService {


    @Autowired
    private FermeRepository fermeRepository;

    @Autowired
    private FermeSearchRepository fermeSearchRepository;


    @Override
    public Ferme createFerme(Ferme ferme) {
        if (fermeRepository.findByNomOrLocalisation(ferme.getNom(), ferme.getLocalisation()).isPresent()) {
            throw new IllegalArgumentException("Une ferme avec ce nom ou cette localisation existe déjà.");
        }
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

    @Override
    public List<FermeSearchResultVm> findByCriteria(FermeSearchVm search) {
        if ((search.getNom() == null || search.getNom().trim().isEmpty()) &&
                (search.getLocalisation() == null || search.getLocalisation().trim().isEmpty())) {
            throw new InvalidSearchCriteriaException("Au moins un critère de recherche (nom ou localisation) doit être spécifié.");
        }

        List<FermeSearchResultVm> results = fermeSearchRepository.findByCriteria(search);

        if (results.isEmpty()) {
            throw new NoResultsFoundException("Aucun résultat trouvé pour les critères donnés.");
        }

        return results;
    }



}
