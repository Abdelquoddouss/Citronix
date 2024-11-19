package com.java.citronix.repository;

import com.java.citronix.domaine.entities.Champ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChampRepository extends JpaRepository<Champ, UUID> {

}
