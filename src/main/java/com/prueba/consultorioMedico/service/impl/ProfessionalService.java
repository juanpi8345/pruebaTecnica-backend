package com.prueba.consultorioMedico.service.impl;

import com.prueba.consultorioMedico.model.MedicalAppointment;
import com.prueba.consultorioMedico.model.Professional;
import com.prueba.consultorioMedico.model.Speciality;
import com.prueba.consultorioMedico.repository.IProfessionalRepository;
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

    @Override
    public List<Professional> findAll() {
        return professionalRepository.findAll();
    }

    @Override
    public void add(Professional professional) {
        if(professional.getMedicalAppointments() == null)
            professional.setMedicalAppointments(new ArrayList<MedicalAppointment>());
        if(professional.getSpecialityList() == null)
            professional.setSpecialityList(new ArrayList<Speciality>());
        professionalRepository.save(professional);
    }
}
