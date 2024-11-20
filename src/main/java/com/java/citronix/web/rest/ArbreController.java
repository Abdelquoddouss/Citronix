package com.java.citronix.web.rest;

import com.java.citronix.domaine.entities.Arbre;
import com.java.citronix.service.ArbreService;
import com.java.citronix.web.vm.ArbreVm;
import com.java.citronix.web.vm.mappers.ArbreMapper;
import com.java.citronix.web.vm.response.ArbreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/arbres")
@RequiredArgsConstructor
public class ArbreController {

    private final ArbreService arbreService;
    private final ArbreMapper arbreMapper;

    @PostMapping("/{champId}")
    public ResponseEntity<ArbreResponse> createArbre(@RequestBody ArbreVm arbreVm, @PathVariable UUID champId) {
        Arbre arbre = arbreMapper.toEntity(arbreVm);
        Arbre createdArbre = arbreService.createArbre(arbre, champId);
        return ResponseEntity.ok(arbreMapper.toResponse(createdArbre));
    }

    @PutMapping("/{arbreId}")
    public ResponseEntity<ArbreResponse> updateArbre(@PathVariable UUID arbreId, @RequestBody ArbreVm arbreVm) {
        Arbre arbreDetails = arbreMapper.toEntity(arbreVm);
        Arbre updatedArbre = arbreService.updateArbre(arbreId, arbreDetails);
        return ResponseEntity.ok(arbreMapper.toResponse(updatedArbre));
    }

    @GetMapping("/{arbreId}")
    public ResponseEntity<ArbreResponse> getArbreById(@PathVariable UUID arbreId) {
        Arbre arbre = arbreService.getArbreById(arbreId);
        return ResponseEntity.ok(arbreMapper.toResponse(arbre));
    }

    @GetMapping("/champ/{champId}")
    public ResponseEntity<List<ArbreResponse>> getArbresByChamp(@PathVariable UUID champId) {
        List<Arbre> arbres = arbreService.getArbresByChamp(champId);
        List<ArbreResponse> response = arbres.stream()
                .map(arbreMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


}