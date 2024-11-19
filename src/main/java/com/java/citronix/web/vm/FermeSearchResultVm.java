package com.java.citronix.web.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FermeSearchResultVm {
    private String nom;
    private String localisation;
    private LocalDate dateCreation;
}