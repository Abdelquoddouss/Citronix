package com.java.citronix.web.vm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ArbreVm {
    private LocalDate datePlantation;
}