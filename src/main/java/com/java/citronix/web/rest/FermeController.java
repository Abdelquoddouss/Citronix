package com.java.citronix.web.rest;


import com.java.citronix.domaine.entities.Ferme;
import com.java.citronix.service.FermeService;
import com.java.citronix.web.vm.FermeSearchResultVm;
import com.java.citronix.web.vm.FermeSearchVm;
import com.java.citronix.web.vm.FermeVm;
import com.java.citronix.web.vm.mappers.FermeMapper;
import com.java.citronix.web.vm.response.FermeResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
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


    @GetMapping("/{id}")
    public ResponseEntity<FermeVm> getFermeById(@PathVariable @Valid UUID id) {
        Ferme ferme = fermeService.getFermeById(id);
        return ResponseEntity.ok(fermeMapper.toVm(ferme));
    }

    @GetMapping("/all")
    public ResponseEntity<List<FermeVm>> getAllFermes() {
        List<Ferme> fermes = fermeService.getAllFermes();
        List<FermeVm> fermeVms = fermes.stream()
                .map(fermeMapper::toVm)
                .toList();
        return ResponseEntity.ok(fermeVms);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFerme(@PathVariable UUID id) {
        fermeService.deleteFerme(id);
        return ResponseEntity.ok("Ferme deleted successfully");
    }


    @GetMapping("/search")
    public ResponseEntity<List<FermeSearchResultVm>> searchFarm(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String localisation,
            @RequestParam(required = false) String dateCreation) {

        LocalDate creationDateParsed = null;
        if (StringUtils.hasText(dateCreation)) {
            try {
                creationDateParsed = LocalDate.parse(dateCreation);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        FermeSearchVm searchDTO = new FermeSearchVm(nom, localisation, creationDateParsed);
        List<FermeSearchResultVm> farms = fermeService.findByCriteria(searchDTO);
        return ResponseEntity.ok(farms);
    }



}
