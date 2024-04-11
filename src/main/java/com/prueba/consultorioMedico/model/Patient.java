package com.prueba.consultorioMedico.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="patients")
public class Patient {
    @Id
    @Column(name = "patient_dni")
    private String dni;
    private String name;
    private String lastname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private List<MedicalAppointment> medicalAppointments;
}
