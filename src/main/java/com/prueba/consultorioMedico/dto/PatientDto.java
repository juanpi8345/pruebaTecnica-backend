package com.prueba.consultorioMedico.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class PatientDto {
    private String dni;
    private String name;
    private String lastname;
}
