package com.prueba.consultorioMedico.service.impl;

import com.prueba.consultorioMedico.model.MedicalAppointment;
import com.prueba.consultorioMedico.model.Patient;
import com.prueba.consultorioMedico.repository.IPatientRepository;
import com.prueba.consultorioMedico.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private IPatientRepository patientRepository;

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public void add(Patient patient) {
        patient.setMedicalAppointments(new ArrayList<MedicalAppointment>());
        patientRepository.save(patient);
    }
}
