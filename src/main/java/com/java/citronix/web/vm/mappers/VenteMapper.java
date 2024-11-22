package com.java.citronix.web.vm.mappers;

import com.java.citronix.domaine.entities.Vente;
import com.java.citronix.web.vm.VenteVm;
import com.java.citronix.web.vm.response.VenteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VenteMapper {

    Vente toEntity(VenteVm venteVm);

    VenteResponse toResponse(Vente vente);
}
