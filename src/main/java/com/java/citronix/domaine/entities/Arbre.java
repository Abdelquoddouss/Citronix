package com.java.citronix.domaine.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Arbre {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDate datePlantation;

    @ManyToOne
    @JoinColumn(name = "champ_id")
    private Champ champ;


    @OneToMany(mappedBy = "arbre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailRecolte> detailsRecolte;



    @Transient
    public int getAge() {
        return datePlantation != null ? Period.between(datePlantation, LocalDate.now()).getYears()  : 0;
    }

    @Transient
    public double getProductivite() {
        int age = getAge();
        if (age < 3) {
            return 2.5;
        } else if (age <= 10) {
            return 12.0;
        } else {
            return 20.0;
        }
    }

}
