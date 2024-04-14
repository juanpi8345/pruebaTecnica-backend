package com.prueba.consultorioMedico.service;

import com.prueba.consultorioMedico.dto.FullMedicalAppointmentDto;
import com.prueba.consultorioMedico.dto.SimpleMedicalAppointmentDto;
import com.prueba.consultorioMedico.model.Patient;
import com.prueba.consultorioMedico.model.Professional;
import com.prueba.consultorioMedico.model.Speciality;

import java.time.LocalDateTime;
import java.util.List;

public interface IMedicalAppointmentService extends IGenericService<FullMedicalAppointmentDto> {
    List<FullMedicalAppointmentDto> findAllByPatient(String patientDni);
    List<FullMedicalAppointmentDto> findAllByProfessional(String professionalDni);
    List<FullMedicalAppointmentDto> findAllBySpeciality(String specialityName);
    void deleteAppointment(Long appointmentId);
    void updateAppointment(Long appointmentId, LocalDateTime newDate);
    //Sobre cargo el metodo para poder agregar de manera mas sencilla
    void add(SimpleMedicalAppointmentDto simpleMedicalAppointmentDto);
    void cancel(Long medicalAppointmentId);

}
