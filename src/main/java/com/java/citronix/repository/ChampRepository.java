package com.java.citronix.repository;

import com.java.citronix.domaine.entities.Champ;
import com.java.citronix.domaine.entities.Ferme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChampRepository extends JpaRepository<Champ, UUID> {
    List<Champ> findByFerme(Ferme ferme);

}
