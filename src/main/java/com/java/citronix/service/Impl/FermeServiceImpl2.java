package com.java.citronix.service.Impl;


import com.java.citronix.domaine.entities.Champ;
import com.java.citronix.domaine.entities.Ferme;
import com.java.citronix.repository.FermeRepository;
import com.java.citronix.service.FermeService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("fermeService2")
@RequiredArgsConstructor
public class FermeServiceImpl2 implements FermeService {

     private final FermeRepository   fermeRepository;



    @Override
    public Ferme createFerme(Ferme ferme) {
        Optional<Ferme> farmOptional = Optional.ofNullable(fermeRepository.findByNom(ferme.getNom()));
        if (farmOptional.isPresent()) {
            throw new RuntimeException("Farm already exists");
        }
        if (ferme.getChamps() == null || ferme.getChamps().isEmpty()) {
            throw new IllegalArgumentException("Ferme is null ");
        }
        return fermeRepository.save(ferme);
    }

    @Override
    public Ferme updateFerme(UUID id, Ferme ferme) {
        return null;
    }

    @Override
    public Ferme getFermeById(UUID id) {
        return null;
    }

    @Override
    public List<Ferme> getAllFermes() {
        return List.of();
    }

    @Override
    public void deleteFerme(UUID id) {

    }


    public List<Ferme> getFermeWithSperficie() {
        List<Ferme> allFarms = getAllFermes();
        return allFarms.stream().filter(farm -> farm.getChamps().stream()
                .mapToDouble(Champ::getSuperficie)
                .sum() > 400
        ).toList();
    }
}
