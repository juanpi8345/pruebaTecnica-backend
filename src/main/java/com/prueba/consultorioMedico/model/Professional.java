package com.prueba.consultorioMedico.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="professionals")
public class Professional {
    @Id
    @Column(name = "professional_dni")
    private String dni;
    private String name;
    private String lastname;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "professional_specialities",
            joinColumns = @JoinColumn(name="professional_dni"),
            inverseJoinColumns = @JoinColumn(name="speciality_name")
    )
    private List<Speciality> specialityList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "professional")
    private List<MedicalAppointment> medicalAppointments;
    private LocalTime start;
    private LocalTime end;

}
