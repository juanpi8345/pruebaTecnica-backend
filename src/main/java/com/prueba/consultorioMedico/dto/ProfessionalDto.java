package com.prueba.consultorioMedico.dto;

import lombok.*;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class ProfessionalDto {
    private String dni;
    private String name;
    private String lastname;
    private LocalTime start;
    private LocalTime end;
}
