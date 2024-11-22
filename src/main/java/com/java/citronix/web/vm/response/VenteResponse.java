package com.java.citronix.web.vm.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class VenteResponse {

    private UUID id;
    private LocalDate dateVente;
    private Double quantiteVendue;
    private Double prixUnitaire;
    private String nomClient;
}
