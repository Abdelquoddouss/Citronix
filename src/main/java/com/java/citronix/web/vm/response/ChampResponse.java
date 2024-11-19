package com.java.citronix.web.vm.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChampResponse {

    private UUID id;
    private Double superficie;
    private UUID fermeId;
}
