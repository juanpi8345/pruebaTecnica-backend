package com.prueba.consultorioMedico.service;

import com.prueba.consultorioMedico.dto.MedicalAppointmentDto;
import com.prueba.consultorioMedico.model.MedicalAppointment;
import com.prueba.consultorioMedico.model.Patient;
import com.prueba.consultorioMedico.model.Professional;
import com.prueba.consultorioMedico.model.Speciality;

import java.time.LocalDateTime;
import java.util.List;

public interface IMedicalAppointmentService extends IGenericService<MedicalAppointmentDto> {
    List<MedicalAppointmentDto> findAllByPatient(Patient patient);
    List<MedicalAppointmentDto> findAllByProfessional(Professional professional);
    List<MedicalAppointmentDto> findAllBySpeciality(Speciality speciality);
    void deleteAppointment(Long appointmentId);
    void updateAppointment(MedicalAppointmentDto medicalAppointmentDto);

}
