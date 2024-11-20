package com.java.citronix.service;

import com.java.citronix.domaine.entities.Arbre;

import java.util.UUID;

public interface ArbreService {
    Arbre createArbre(Arbre arbre, UUID champId);

    Arbre updateArbre(UUID arbreId, Arbre arbreDetails);

    Arbre getArbreById(UUID arbreId);


}
