package com.prueba.consultorioMedico.service.impl;

import com.prueba.consultorioMedico.model.Professional;
import com.prueba.consultorioMedico.model.Speciality;
import com.prueba.consultorioMedico.repository.ISpecialityRepository;
import com.prueba.consultorioMedico.service.ISpecialityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SpecialityService implements ISpecialityService {

    @Autowired
    private ISpecialityRepository specialityRepository;

    @Override
    public List<Speciality> findAll() {
        return specialityRepository.findAll();
    }

    @Override
    public void add(Speciality speciality) {
        speciality.setProfessionalList(new ArrayList<Professional>());
        specialityRepository.save(speciality);
    }
}
