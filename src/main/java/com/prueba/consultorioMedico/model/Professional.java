package com.prueba.consultorioMedico.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    // Lista de profesionales con sus correspondientes especialidades y horarios.
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "professional_specialities",
            joinColumns = @JoinColumn(name="professional_dni"),
            inverseJoinColumns = @JoinColumn(name="speciality_name")
    )
    private Set<Speciality> specialityList = new HashSet<>();
    private LocalTime start;
    private LocalTime end;

}
