package com.java.citronix.service.Impl;

import com.java.citronix.domaine.entities.Arbre;
import com.java.citronix.domaine.entities.Champ;
import com.java.citronix.exception.ResourceNotFoundException;
import com.java.citronix.repository.ArbreRepository;
import com.java.citronix.repository.ChampRepository;
import com.java.citronix.service.ArbreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ArbreServiceImpl implements ArbreService {

    @Autowired
    private ArbreRepository arbreRepository;

    @Autowired
    private ChampRepository champRepository;

    @Override
    public Arbre createArbre(Arbre arbre, UUID champId) {
        Champ champ = champRepository.findById(champId)
                .orElseThrow(() -> new ResourceNotFoundException("Champ not found with ID: " + champId));
        arbre.setChamp(champ);
        return arbreRepository.save(arbre);
    }

    @Override
    public Arbre updateArbre(UUID arbreId, Arbre arbreDetails) {
        return arbreRepository.findById(arbreId)
                .map(existingArbre -> {
                    existingArbre.setDatePlantation(arbreDetails.getDatePlantation());
                    return arbreRepository.save(existingArbre);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Arbre not found with ID: " + arbreId));
    }

    @Override
    public Arbre getArbreById(UUID arbreId) {
        return arbreRepository.findById(arbreId)
                .orElseThrow(() -> new ResourceNotFoundException("Arbre not found with ID: " + arbreId));
    }

    @Override
    public Page<Arbre> getArbresByChamp(UUID champId, Pageable pageable) {
        return arbreRepository.findByChampId(champId,pageable);
    }


    @Override
    public void deleteArbre(UUID arbreId) {
        Arbre arbre = arbreRepository.findById(arbreId)
                .orElseThrow(() -> new ResourceNotFoundException("Arbre not found with ID: " + arbreId));
        arbreRepository.delete(arbre);
    }



}
