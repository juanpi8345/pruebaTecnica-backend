package com.prueba.consultorioMedico.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="medical_appointments")
public class MedicalAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_appointment_id")
    private Long medicalAppointmentId;
    @ManyToOne
    @JoinColumn(name = "patient_dni")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "professional_dni")
    private Professional professional;
    @ManyToOne
    @JoinColumn(name = "consultingRoomName")
    private ConsultingRoom consultingRoom;
    private LocalDateTime appointmentDate;
}
