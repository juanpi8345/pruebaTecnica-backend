package com.prueba.consultorioMedico.service.impl;

import com.prueba.consultorioMedico.model.MedicalAppointment;
import com.prueba.consultorioMedico.model.Patient;
import com.prueba.consultorioMedico.model.Professional;
import com.prueba.consultorioMedico.model.Speciality;
import com.prueba.consultorioMedico.repository.IMedicalAppointmentRepository;
import com.prueba.consultorioMedico.service.IMedicalAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalAppointmentService implements IMedicalAppointmentService {

    @Autowired
    private IMedicalAppointmentRepository medicalRepository;

    @Override
    public List<MedicalAppointment> findAllByPatient(Patient patient) {
        return medicalRepository.findAllByPatient(patient);
    }

    @Override
    public List<MedicalAppointment> findAllByProfessional(Professional professional) {
        return medicalRepository.findAllByProfessional(professional);
    }

    @Override
    public List<MedicalAppointment> findAllBySpeciality(Speciality speciality) {
        return medicalRepository.findAllBySpeciality(speciality);
    }

    @Override
    public List<MedicalAppointment> findAll() {
        return medicalRepository.findAll();
    }
}
