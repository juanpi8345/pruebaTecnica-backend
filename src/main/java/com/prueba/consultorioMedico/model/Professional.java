package com.prueba.consultorioMedico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
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
            inverseJoinColumns = @JoinColumn(name="speciality_id")
    )
    private List<Speciality> specialityList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "professional")
    private List<MedicalAppointment> medicalAppointment;
    private LocalTime start;
    private LocalTime end;

}
