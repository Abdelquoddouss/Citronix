package com.java.citronix.web.vm;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FermeSearchVm {

    private String nom;
    private String localisation;
    private LocalDate dateCreation;
}
