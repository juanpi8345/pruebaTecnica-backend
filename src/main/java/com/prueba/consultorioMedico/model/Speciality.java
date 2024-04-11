package com.prueba.consultorioMedico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="specialities")
public class Speciality {
    @Id
    @Column(name = "speciality_name")
    private String name;
    @ManyToMany(mappedBy = "specialityList")
    private List<Professional> professionalList = new ArrayList<>();
}
