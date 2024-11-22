package com.java.citronix.service;

import com.java.citronix.domaine.entities.Vente;

import java.util.List;
import java.util.UUID;

public interface VenteService {
    Vente createVente(Vente vente);
    Vente getVenteById(UUID venteId);
    List<Vente> getAllVentes();
    void deleteVente(UUID venteId);
    Double calculerRevenu(UUID venteId);
    List<Vente> getHistoriqueVentes();

}
