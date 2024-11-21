package com.java.citronix.web.rest;


import com.java.citronix.domaine.entities.Recolte;
import com.java.citronix.service.RecolteService;
import com.java.citronix.web.vm.RecolteVm;
import com.java.citronix.web.vm.mappers.RecolteMapper;
import com.java.citronix.web.vm.response.RecolteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recoltes")
@RequiredArgsConstructor
public class RecolteController {

    private final RecolteService recolteService;
    private final RecolteMapper recolteMapper;


    @PostMapping
    public ResponseEntity<RecolteResponse> createRecolte(@RequestBody RecolteVm recolteVm) {
        Recolte recolte = recolteMapper.toEntity(recolteVm);
        Recolte createdRecolte = recolteService.createRecolte(recolte);
        return ResponseEntity.ok(recolteMapper.toResponse(createdRecolte));
    }

}
