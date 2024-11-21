package com.java.citronix.service;

import com.java.citronix.domaine.entities.Recolte;

import java.util.List;
import java.util.UUID;

public interface RecolteService {
    Recolte createRecolte(Recolte recolte);
    Recolte getRecolteById(UUID recolteId);
    List<Recolte> getAllRecoltes();
    void deleteRecolte(UUID recolteId);
}
