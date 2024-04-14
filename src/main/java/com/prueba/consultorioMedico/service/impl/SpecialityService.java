package com.prueba.consultorioMedico.service.impl;

import com.prueba.consultorioMedico.model.Professional;
import com.prueba.consultorioMedico.model.Speciality;
import com.prueba.consultorioMedico.repository.IProfessionalRepository;
import com.prueba.consultorioMedico.repository.ISpecialityRepository;
import com.prueba.consultorioMedico.service.IProfessionalService;
import com.prueba.consultorioMedico.service.ISpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialityService implements ISpecialityService {

    @Autowired
    private ISpecialityRepository specialityRepository;

    @Autowired
    private IProfessionalRepository professionalRepository;

    @Override
    public List<Speciality> findAll() {
        return specialityRepository.findAll();
    }

    @Override
    public void add(Speciality speciality) {
        specialityRepository.save(speciality);
    }

    @Override
    public List<Speciality> findSpecialitiesByProfessional(String professionalDni) {
       Professional professional = professionalRepository.findById(professionalDni).orElseThrow();
       return specialityRepository.findAllByProfessional(professional);
    }
}
