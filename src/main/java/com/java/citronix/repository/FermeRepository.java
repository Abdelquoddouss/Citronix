package com.java.citronix.repository;


import com.java.citronix.domaine.entities.Ferme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FermeRepository extends JpaRepository<Ferme, UUID> {

}
