package com.java.citronix.web.vm.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ArbreResponse {
    private UUID id;
    private LocalDate datePlantation;
    private UUID champId;
}