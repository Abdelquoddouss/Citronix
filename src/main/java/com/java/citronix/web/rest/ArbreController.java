package com.java.citronix.web.rest;

import com.java.citronix.domaine.entities.Arbre;
import com.java.citronix.service.ArbreService;
import com.java.citronix.web.vm.ArbreVm;
import com.java.citronix.web.vm.mappers.ArbreMapper;
import com.java.citronix.web.vm.response.ArbreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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



}