package com.java.citronix.service;

import com.java.citronix.domaine.entities.Ferme;
import com.java.citronix.web.vm.FermeSearchResultVm;
import com.java.citronix.web.vm.FermeSearchVm;
import com.java.citronix.web.vm.FermeVm;
import org.hibernate.query.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

public interface FermeService {

    Ferme createFerme(Ferme ferme);

   Ferme updateFerme(UUID id, Ferme ferme);

    Ferme getFermeById(UUID id);

   List<Ferme> getAllFermes();


    void deleteFerme(UUID id);

    List<FermeSearchResultVm> findByCriteria(FermeSearchVm search);


    }
