package com.java.citronix.web.vm;

import com.java.citronix.domaine.enums.Saison;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class RecolteVm {

    @NotNull(message = "dateRecolte is required")
    private LocalDate dateRecolte;

    @NotNull(message = "saison is required")
    private Saison saison;

    @NotNull(message = "quantiteTotal is required")
    private Double quantiteTotal;
}