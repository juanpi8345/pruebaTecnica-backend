package com.prueba.consultorioMedico.service;

import com.prueba.consultorioMedico.dto.FullMedicalAppointmentDto;
import com.prueba.consultorioMedico.model.Patient;
import com.prueba.consultorioMedico.model.Professional;
import com.prueba.consultorioMedico.model.Speciality;

import java.time.LocalDateTime;
import java.util.List;

public interface IMedicalAppointmentService extends IGenericService<FullMedicalAppointmentDto> {
    List<FullMedicalAppointmentDto> findAllByPatient(Patient patient);
    List<FullMedicalAppointmentDto> findAllByProfessional(Professional professional);
    List<FullMedicalAppointmentDto> findAllBySpeciality(Speciality speciality);
    void deleteAppointment(Long appointmentId);
    void updateAppointment(Long appointmentId, LocalDateTime newDate);

}
