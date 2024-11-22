package com.java.citronix.domaine.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Vente {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    private UUID id;


    private LocalDate dateVente;

    private Double quantiteVendue;

    private Double prixUnitaire;

    private String nomClient;



    @ManyToOne
    @JoinColumn(name = "recolte_id")
    private Recolte recolte;

    @Transient
    public Double calculerRevenu() {
        if (quantiteVendue != null && prixUnitaire != null) {
            return quantiteVendue * prixUnitaire;
        }
        return 0.0;
    }


}
