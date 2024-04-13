package com.prueba.consultorioMedico.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    //Evitar bucle //Considerar borrarlo
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", fetch = FetchType.LAZY)
    private Set<MedicalAppointment> medicalAppointments = new HashSet<>();
}
