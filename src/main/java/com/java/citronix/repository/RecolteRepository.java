package com.java.citronix.repository;

import com.java.citronix.domaine.entities.Recolte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecolteRepository extends JpaRepository<Recolte, UUID> {
}
