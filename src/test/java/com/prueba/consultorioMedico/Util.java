package com.prueba.consultorioMedico;

import com.prueba.consultorioMedico.dto.FullMedicalAppointmentDto;
import com.prueba.consultorioMedico.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static Patient patient =
            Patient.builder().dni("45199461").name("Juan").lastname("Pregliasco").build();;
    public static Patient patient2 =
            Patient.builder().dni("45199462").name("Juana").lastname("Pregliasca").build();;
    public static Professional  professional =
            Professional.builder().dni("30400200").name("Fernando").lastname("Florez").build();
    public static Professional professional2 =
            Professional.builder().dni("20400200").name("Fernanda").lastname("Florez").build();
    public static ConsultingRoom consultingRoom =
            ConsultingRoom.builder().consultingRoomName("1").build();
    public static Speciality speciality = Speciality.builder().name("Dermatologia").build();

    public static List<MedicalAppointment> medicalAppointments = new ArrayList<>();

    public static void addMedicalAppointments(){
        medicalAppointments.add(MedicalAppointment.builder().
                medicalAppointmentId(1L)
                .consultingRoom(consultingRoom).professional(professional).speciality(null)
                .appointmentDate(LocalDateTime.of(2024,05,20,20,30))
                .patient(patient).speciality(speciality).build()
        );
        medicalAppointments.add(MedicalAppointment.builder().
                medicalAppointmentId(2L)
                .consultingRoom(consultingRoom).professional(professional).speciality(null)
                .appointmentDate(LocalDateTime.of(2024,06,20,20,30))
                .patient(patient).speciality(speciality).build()
        );
        medicalAppointments.add(MedicalAppointment.builder().
                medicalAppointmentId(3L)
                .consultingRoom(consultingRoom).professional(professional2).speciality(null)
                .appointmentDate(LocalDateTime.of(2024,05,20,20,30))
                .patient(patient2).speciality(speciality).build()
        );
    }

    public static List<FullMedicalAppointmentDto> formatList(List<MedicalAppointment> medicalAppointmentList){
        List<FullMedicalAppointmentDto> fullMedicalAppointmentDto = new ArrayList<>();
        medicalAppointmentList.forEach((m)->{
            fullMedicalAppointmentDto.add(FullMedicalAppointmentDto.builder()
                    .date(m.getAppointmentDate())
                    .patientLastname(m.getPatient().getLastname())
                    .patientDni(m.getPatient().getDni())
                    .patientName(m.getPatient().getName())
                    .professionalDni(m.getProfessional().getDni())
                    .professionalName(m.getProfessional().getName())
                    .professionalLastname(m.getProfessional().getLastname())
                    .consultingRoomName(m.getConsultingRoom().getConsultingRoomName())
                    .specialityName(m.getSpeciality().getName()).build());
        });
        return fullMedicalAppointmentDto;
    }
}
