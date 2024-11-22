package com.java.citronix.repository;

import com.java.citronix.domaine.entities.Ferme;
import com.java.citronix.web.vm.FermeSearchResultVm;
import com.java.citronix.web.vm.FermeSearchVm;
import com.java.citronix.web.vm.FermeVm;

import java.util.List;

public interface  FermeSearchRepository {
     List<FermeSearchResultVm> findByCriteria(FermeSearchVm search) ;
}
