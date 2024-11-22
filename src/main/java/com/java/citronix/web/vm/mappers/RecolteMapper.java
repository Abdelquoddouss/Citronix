package com.java.citronix.web.vm.mappers;


import com.java.citronix.domaine.entities.DetailRecolte;
import com.java.citronix.domaine.entities.Recolte;
import com.java.citronix.web.vm.DetailRecoletVm;
import com.java.citronix.web.vm.RecolteVm;
import com.java.citronix.web.vm.response.RecolteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecolteMapper {



    Recolte toEntity(RecolteVm recolteVm);


    RecolteResponse toResponse(Recolte recolte);




    @Mapping(target = "arbre.id", source = "arbreId")
    DetailRecolte toDetailVm(DetailRecoletVm detailRecoletVm);



}
