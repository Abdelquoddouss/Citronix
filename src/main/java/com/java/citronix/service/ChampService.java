package com.java.citronix.service;

import com.java.citronix.domaine.entities.Champ;

import java.util.List;
import java.util.UUID;

public interface ChampService {

    Champ save(Champ champ, UUID fermeId);
    Champ updateChamp(UUID champId, Champ champDetails);
    Champ getChampById(UUID champId);
    List<Champ> getAllChampsByFerme(UUID fermeId);
//    void deleteChamp(UUID champId);


}
