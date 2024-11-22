package com.java.citronix.web.rest;


import com.java.citronix.domaine.entities.Recolte;
import com.java.citronix.service.RecolteService;
import com.java.citronix.web.vm.RecolteVm;
import com.java.citronix.web.vm.mappers.RecolteMapper;
import com.java.citronix.web.vm.response.RecolteResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/recoltes")
@RequiredArgsConstructor
public class RecolteController {

    private final RecolteService recolteService;
    private final RecolteMapper recolteMapper;


    @PostMapping
    public ResponseEntity<RecolteResponse> createRecolte(@RequestBody @Valid RecolteVm recolteVm) {
        Recolte recolte = recolteMapper.toEntity(recolteVm);
        Recolte createdRecolte = recolteService.createRecolte(recolte);
        return ResponseEntity.ok(recolteMapper.toResponse(createdRecolte));
    }

    @GetMapping("/{recolteId}")
    public ResponseEntity<RecolteResponse> getRecolteById(@PathVariable UUID recolteId) {
        Recolte recolte = recolteService.getRecolteById(recolteId);
        return ResponseEntity.ok(recolteMapper.toResponse(recolte));
    }


    @GetMapping
    public ResponseEntity<List<RecolteResponse>> getAllRecoltes() {
        List<Recolte> recoltes = recolteService.getAllRecoltes();
        List<RecolteResponse> response = recoltes.stream()
                .map(recolteMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{recolteId}")
    public ResponseEntity<String> deleteRecolte(@PathVariable UUID recolteId) {
        recolteService.deleteRecolte(recolteId);
        return ResponseEntity.ok("Recolte successfully deleted.");
    }

}
