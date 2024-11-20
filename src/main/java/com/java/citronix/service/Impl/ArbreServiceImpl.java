package com.java.citronix.service.Impl;

import com.java.citronix.domaine.entities.Arbre;
import com.java.citronix.domaine.entities.Champ;
import com.java.citronix.exception.ResourceNotFoundException;
import com.java.citronix.repository.ArbreRepository;
import com.java.citronix.repository.ChampRepository;
import com.java.citronix.service.ArbreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
