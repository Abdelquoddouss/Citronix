package com.java.citronix.repository;

import com.java.citronix.domaine.entities.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VenteRepository extends JpaRepository<Vente, UUID> {
}
