package com.java.citronix.web.rest;


import com.java.citronix.domaine.entities.Ferme;
import com.java.citronix.service.FermeService;
import com.java.citronix.web.vm.FermeVm;
import com.java.citronix.web.vm.mappers.FermeMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/ferme")
@AllArgsConstructor
public class FermeController {

    private final FermeService fermeService;
    private final FermeMapper fermeMapper;

    @PostMapping("/create")
    public ResponseEntity<String> createFerme(@Valid @RequestBody FermeVm fermeVm) {
        Ferme ferme = fermeMapper.toEntity(fermeVm);
        fermeService.createFerme(ferme);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ferme created successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FermeVm> updateFerme(@PathVariable UUID id, @Valid @RequestBody FermeVm fermeVm) {

        Ferme fermeDetails = fermeMapper.toEntity(fermeVm);
        Ferme updatedFerme = fermeService.updateFerme(id, fermeDetails);
        return ResponseEntity.ok(fermeMapper.toVm(updatedFerme));

    }



}