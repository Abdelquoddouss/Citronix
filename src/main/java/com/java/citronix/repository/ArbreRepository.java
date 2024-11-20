package com.java.citronix.repository;

import com.java.citronix.domaine.entities.Arbre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArbreRepository extends JpaRepository<Arbre, UUID> {

}
