package com.java.citronix.web.vm;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DetailRecoletVm {

    @NotNull(message = "arbre ID is required.")
    private UUID arbreId;
}
