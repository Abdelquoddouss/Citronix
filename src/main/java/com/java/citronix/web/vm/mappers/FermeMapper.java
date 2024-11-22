package com.java.citronix.web.vm.mappers;

import com.java.citronix.domaine.entities.Ferme;
import com.java.citronix.web.vm.FermeVm;
import org.springframework.web.bind.annotation.Mapping;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface FermeMapper {

    Ferme toEntity(FermeVm fermeVm);

    FermeVm toVm(Ferme ferme);

}
