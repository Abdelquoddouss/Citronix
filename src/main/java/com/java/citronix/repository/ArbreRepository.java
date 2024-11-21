package com.java.citronix.repository;

import com.java.citronix.domaine.entities.Arbre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ArbreRepository extends JpaRepository<Arbre, UUID> {

    Page<Arbre> findByChampId(UUID champId, Pageable pageable);
}
