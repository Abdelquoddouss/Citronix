package com.java.citronix.web.vm.response;


import com.java.citronix.domaine.entities.Champ;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FermeResponse {

    private String nom;
    private String localisation;
    private Double superficie;
    private String dateCreation;
    private List<Champ> ChampList;

}
