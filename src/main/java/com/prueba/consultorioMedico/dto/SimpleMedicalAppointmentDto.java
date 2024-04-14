package com.prueba.consultorioMedico.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//Dto con el objetivo de recibir informacion
public class SimpleMedicalAppointmentDto {
    String professionalDni;
    String patientDni;
    String consultingRoomName;
    String specialityName;
    LocalDateTime date;
}
