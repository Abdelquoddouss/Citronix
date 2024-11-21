package com.java.citronix.web.rest;

import com.java.citronix.domaine.entities.Vente;
import com.java.citronix.web.vm.response.VenteResponse;
import com.java.citronix.service.VenteService;
import com.java.citronix.web.vm.VenteVm;
import com.java.citronix.web.vm.mappers.VenteMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/ventes")
public class VenteController {

    @Autowired
    private VenteService venteService;

    @Autowired
    private VenteMapper venteMapper;

    // Ajouter une vente
    @PostMapping
    public ResponseEntity<VenteResponse> createVente(@Valid @RequestBody VenteVm venteVm) {
        Vente vente = venteMapper.toEntity(venteVm);
        Vente savedVente = venteService.createVente(vente);
        VenteResponse response = venteMapper.toResponse(savedVente);
        return ResponseEntity.ok(response);
    }

    // Récupérer toutes les ventes
    @GetMapping
    public ResponseEntity<List<VenteResponse>> getAllVentes() {
        List<Vente> ventes = venteService.getAllVentes();
        List<VenteResponse> response = ventes.stream()
                .map(venteMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    // Récupérer une vente par son ID
    @GetMapping("/{id}")
    public ResponseEntity<VenteResponse> getVenteById(@PathVariable UUID id) {
        Vente vente = venteService.getVenteById(id);
        VenteResponse response = venteMapper.toResponse(vente);
        return ResponseEntity.ok(response);
    }

    // Supprimer une vente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVente(@PathVariable UUID id) {
        venteService.deleteVente(id);
        return ResponseEntity.noContent().build();
    }
}
