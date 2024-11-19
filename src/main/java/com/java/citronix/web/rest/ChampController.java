package com.java.citronix.web.rest;

import com.java.citronix.domaine.entities.Champ;
import com.java.citronix.service.ChampService;
import com.java.citronix.web.vm.ChampVm;
import com.java.citronix.web.vm.mappers.ChampMapper;
import com.java.citronix.web.vm.response.ChampResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/champs")
@AllArgsConstructor
public class ChampController {

    private final ChampService champService;

    private final ChampMapper champMapper;

    @PostMapping("/{fermeId}")
    public ResponseEntity<ChampResponse> createChamp(@RequestBody ChampVm champVm, @PathVariable UUID fermeId) {
        Champ champ = champMapper.toEntity(champVm);
        Champ createdChamp = champService.save(champ, fermeId);
        return ResponseEntity.ok(champMapper.toResponse(createdChamp));
    }


    @PutMapping("/{champId}")
    public ResponseEntity<ChampResponse> updateChamp(
            @PathVariable UUID champId,
            @RequestBody ChampVm champVm) {
        Champ champDetails = champMapper.toEntity(champVm);
        Champ updatedChamp = champService.updateChamp(champId, champDetails);
        return ResponseEntity.ok(champMapper.toResponse(updatedChamp));
    }


    @GetMapping("/{champId}")
    public ResponseEntity<ChampResponse> getChampById(@PathVariable UUID champId) {
        Champ champ = champService.getChampById(champId);
        return ResponseEntity.ok(champMapper.toResponse(champ));
    }


}