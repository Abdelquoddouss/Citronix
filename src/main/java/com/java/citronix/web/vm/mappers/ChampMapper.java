package com.java.citronix.web.vm.mappers;

import com.java.citronix.domaine.entities.Champ;
import com.java.citronix.dto.ChampWithFermeResponse;
import com.java.citronix.web.vm.ChampVm;
import com.java.citronix.web.vm.response.ChampResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChampMapper {


    ChampVm toVm(Champ champ);

    Champ toEntity(ChampVm champVm);

    @Mapping(target = "fermeId", source = "ferme.id")
    ChampResponse toResponse(Champ champ);

    @Mapping(target = "champId", source = "id")
    @Mapping(target = "fermeId", source = "ferme.id")
    @Mapping(target = "fermeNom", source = "ferme.nom")
    ChampWithFermeResponse toChampWithFermeResponse(Champ champ);

}
