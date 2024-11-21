package com.java.citronix.service;

import com.java.citronix.domaine.entities.Arbre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ArbreService {
    Arbre createArbre(Arbre arbre, UUID champId);

    Arbre updateArbre(UUID arbreId, Arbre arbreDetails);

    Arbre getArbreById(UUID arbreId);

    Page<Arbre> getArbresByChamp(UUID champId , Pageable pageable);

  void deleteArbre(UUID arbreId);

}