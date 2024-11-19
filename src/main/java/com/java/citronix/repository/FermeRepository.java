package com.java.citronix.repository;


import com.java.citronix.domaine.entities.Ferme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FermeRepository extends JpaRepository<Ferme, UUID> {

    @Query("SELECT f FROM Ferme f WHERE " +
            "(LOWER(f.nom) LIKE LOWER(CONCAT('%', :nom, '%')) OR :nom IS NULL) AND " +
            "(LOWER(f.localisation) LIKE LOWER(CONCAT('%', :localisation, '%')) OR :localisation IS NULL)")
    List<Ferme> searchFermes(@Param("nom") String nom, @Param("localisation") String localisation);

    List<Ferme> findByMaximumArea(double maxArea);
    Ferme findByNom(String nom);


}
