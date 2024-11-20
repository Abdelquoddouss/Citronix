package com.java.citronix.web.vm.mappers;

import com.java.citronix.domaine.entities.Arbre;
import com.java.citronix.web.vm.ArbreVm;
import com.java.citronix.web.vm.response.ArbreResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArbreMapper {
    Arbre toEntity(ArbreVm arbreVm);

    @Mapping(target = "champId", source = "champ.id")
    ArbreResponse toResponse(Arbre arbre);
}