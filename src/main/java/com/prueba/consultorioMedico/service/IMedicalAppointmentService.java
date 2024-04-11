package com.prueba.consultorioMedico.service;

import com.prueba.consultorioMedico.model.MedicalAppointment;
import com.prueba.consultorioMedico.model.Patient;
import com.prueba.consultorioMedico.model.Professional;
import com.prueba.consultorioMedico.model.Speciality;

import java.util.List;

public interface IMedicalAppointmentService {
    List<MedicalAppointment> findAllByPatient(Patient patient);
    List<MedicalAppointment> findAllByProfessional(Professional professional);
    List<MedicalAppointment> findAllBySpeciality(Speciality speciality);
    List<MedicalAppointment> findAll();
}
