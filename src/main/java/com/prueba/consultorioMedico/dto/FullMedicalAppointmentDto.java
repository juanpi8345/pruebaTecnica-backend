package com.prueba.consultorioMedico.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//Dto con el objetivo de mostrar informacion
public class FullMedicalAppointmentDto {
    Long medicalAppointmentId;
    String professionalDni;
    String professionalName;
    String professionalLastname;
    String patientDni;
    String patientName;
    String patientLastname;
    String consultingRoomName;
    LocalDateTime date;
}
