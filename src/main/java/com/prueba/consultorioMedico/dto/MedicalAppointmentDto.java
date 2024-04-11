package com.prueba.consultorioMedico.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalAppointmentDto {
    String professionalDni;
    String professionalName;
    String professionalLastname;
    String patientDni;
    String patientName;
    String patientLastName;
    String consultingRoomName;
    LocalDateTime date;
    LocalTime professionalTimeStart;
    LocalTime professionalTimeEnd;
}
