package com.java.citronix.domaine.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class DetailRecolte {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    private UUID id;

    private Double quantite;

    @ManyToOne
    @JoinColumn(name = "arbre_id")
    private Arbre arbre;

    @ManyToOne
    @JoinColumn(name = "recolte_id")
    private Recolte recolte;
}
