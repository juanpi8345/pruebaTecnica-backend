package com.prueba.consultorioMedico.model;

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
    @ManyToMany(mappedBy = "specialityList")
    private List<Professional> professionalList = new ArrayList<>();
}
