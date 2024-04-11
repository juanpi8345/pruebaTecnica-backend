package com.prueba.consultorioMedico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="patients")
public class Patient {
    @Id
    @Column(name = "patient_dni")
    private String dni;
    private String name;
    private String lastname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private List<MedicalAppointment> medicalAppointment;
}
