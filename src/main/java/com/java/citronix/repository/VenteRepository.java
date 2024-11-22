package com.java.citronix.repository;

import com.java.citronix.domaine.entities.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface VenteRepository extends JpaRepository<Vente, UUID> {
    List<Vente> findByDateVenteBefore(LocalDate currentDate);

}
