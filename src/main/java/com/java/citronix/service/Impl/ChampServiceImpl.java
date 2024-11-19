package com.java.citronix.service.Impl;

import com.java.citronix.domaine.entities.Champ;
import com.java.citronix.domaine.entities.Ferme;
import com.java.citronix.exception.ResourceNotFoundException;
import com.java.citronix.exception.SuperficieValidationException;
import com.java.citronix.repository.ChampRepository;
import com.java.citronix.repository.FermeRepository;
import com.java.citronix.service.ChampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChampServiceImpl implements ChampService {

    @Autowired
    private ChampRepository champRepository;

    @Autowired
    private FermeRepository fermeRepository;


    @Override
    public Champ save(Champ champ, UUID fermeId) {
        Ferme ferme = fermeRepository.findById(fermeId)
                .orElseThrow(() -> new ResourceNotFoundException("Ferme not found with ID: " + fermeId));

        // Vérifier que la superficie est au moins de 0,1 hectare
        if (champ.getSuperficie() < 0.1) {
            throw new SuperficieValidationException("The field area must be at least 0.1 hectare (1,000 m²).");
        }
        // Calculer la somme des superficies existantes des champs
        double totalSuperficie = ferme.getChamps().stream()
                .mapToDouble(Champ::getSuperficie)
                .sum();
        // Ajouter la superficie du nouveau champ
        totalSuperficie += champ.getSuperficie();

        // Vérifier si la superficie totale dépasse celle de la ferme
        if (totalSuperficie >= ferme.getSuperficie()) {
            throw new SuperficieValidationException("The total area of the fields exceeds the farm's available area.");
        }

        champ.setFerme(ferme);
        return champRepository.save(champ);
    }



    @Override
    public Champ updateChamp(UUID champId, Champ champDetails) {
        Champ existingChamp = champRepository.findById(champId)
                .orElseThrow(() -> new ResourceNotFoundException("Champ not found with ID: " + champId));

        // Vérifier que la superficie est au moins de 0,1 hectare
        if (champDetails.getSuperficie() < 0.1) {
            throw new SuperficieValidationException("The field area must be at least 0.1 hectare (1,000 m²).");
        }
        Ferme ferme = existingChamp.getFerme();

        // Calculer la somme des superficies existantes des champs, en excluant le champ actuel
        double totalSuperficie = ferme.getChamps().stream()
                .filter(champ -> !champ.getId().equals(champId))
                .mapToDouble(Champ::getSuperficie)
                .sum();

        // Ajouter la superficie mise à jour
        totalSuperficie += champDetails.getSuperficie();

        // Vérifier si la superficie totale dépasse celle de la ferme
        if (totalSuperficie >= ferme.getSuperficie()) {
            throw new SuperficieValidationException("The total area of the fields exceeds the farm's available area.");
        }
        existingChamp.setSuperficie(champDetails.getSuperficie());
        // Mettre à jour d'autres champs si nécessaire
        return champRepository.save(existingChamp);
    }


    @Override
    public Champ getChampById(UUID champId) {
        return champRepository.findById(champId)
                .orElseThrow(() -> new ResourceNotFoundException("Champ not found with ID: " + champId));
    }

    @Override
    public List<Champ> getAllChampsByFerme(UUID fermeId) {
        Ferme ferme = fermeRepository.findById(fermeId)
                .orElseThrow(() -> new ResourceNotFoundException("Ferme not found with ID: " + fermeId));
        return champRepository.findByFerme(ferme);
    }

    @Override
    public void deleteChamp(UUID champId) {
        Champ champ = champRepository.findById(champId)
                .orElseThrow(() -> new ResourceNotFoundException("Champ not found with ID: " + champId));

        champRepository.delete(champ);
    }

}
