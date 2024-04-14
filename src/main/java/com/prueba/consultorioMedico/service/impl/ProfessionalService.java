package com.prueba.consultorioMedico.service.impl;

import com.prueba.consultorioMedico.model.MedicalAppointment;
import com.prueba.consultorioMedico.model.Professional;
import com.prueba.consultorioMedico.model.Speciality;
import com.prueba.consultorioMedico.repository.IProfessionalRepository;
import com.prueba.consultorioMedico.repository.ISpecialityRepository;
import com.prueba.consultorioMedico.service.IProfessionalService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
    public class ProfessionalService implements IProfessionalService {

    @Autowired
    private IProfessionalRepository professionalRepository;

    @Autowired
    private ISpecialityRepository specialityRepository;

    @Override
    public List<Professional> findAll() {
        return professionalRepository.findAll();
    }

    @Override
    public void add(Professional professional) {
        professionalRepository.save(professional);
    }

    @Override
    public void addSpeciality(String professionalDni, String specialityName) {
        Professional professional = professionalRepository.findById(professionalDni).orElseThrow();
        Speciality speciality = specialityRepository.findById(specialityName).orElseThrow();
        professional.getSpecialityList().add(speciality);
        speciality.getProfessionalList().add(professional);
        professionalRepository.save(professional);
        specialityRepository.save(speciality);
    }

}
