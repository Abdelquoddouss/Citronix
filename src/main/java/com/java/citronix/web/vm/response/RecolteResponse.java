package com.java.citronix.web.vm.response;

import com.java.citronix.domaine.enums.Saison;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class RecolteResponse {
    private UUID id;
    private LocalDate dateRecolte;
    private Saison saison;
    private Double quantiteTotal;
}
