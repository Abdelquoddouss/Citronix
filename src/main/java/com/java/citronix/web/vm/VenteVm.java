package com.java.citronix.web.vm;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class VenteVm {

    @NotNull(message = "dateVente is required")
    private LocalDate dateVente;

    @NotNull(message = "quantiteVendue is required")
    private Double quantiteVendue;

    @NotNull(message = "prixUnitaire is required")
    private Double prixUnitaire;

    @NotNull(message = "nomClient is required")
    private String nomClient;
}
