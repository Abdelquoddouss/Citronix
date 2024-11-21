package com.java.citronix.repository;

import com.java.citronix.domaine.entities.Arbre;
import com.java.citronix.domaine.entities.Champ;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ArbreRepository extends JpaRepository<Arbre, UUID> {

    Page<Arbre> findByChampId(UUID champId, Pageable pageable);

    boolean existsByChampAndDatePlantation(Champ champ, LocalDate datePlantation);

}
