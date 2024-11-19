package com.java.citronix.web.vm;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class FermeVm {

    @NotNull(message = "Nom is required")
    private String nom;

    @NotNull(message = "Localisation is required")
    private String localisation;

    @NotNull(message = "Superficie is required")
    @Min(value = 0, message = "Superficie must be greater than or equal to 0")
    private Double superficie;

    @NotNull(message = "Date de création is required")
    private LocalDate dateCreation;

    public FermeVm(String nom, String localisation, LocalDate dateCreation) {
        this.nom = nom;
        this.localisation = localisation;
        this.dateCreation = dateCreation;
    }
}
