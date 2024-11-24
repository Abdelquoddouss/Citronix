package com.java.citronix.repository;


import com.java.citronix.domaine.entities.Ferme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FermeRepository extends JpaRepository<Ferme, UUID> {
    Optional<Ferme> findByNomOrLocalisation(String nom, String localisation);


}
