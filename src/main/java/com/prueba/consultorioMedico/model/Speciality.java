package com.prueba.consultorioMedico.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="specialities")
public class Speciality {
    @Id
    @Column(name = "speciality_name")
    private String name;
    //JsonIgnore para evitar un bucle
    @JsonIgnore
    @ManyToMany(mappedBy = "specialityList")
    private List<Professional> professionalList = new ArrayList<>();
}
