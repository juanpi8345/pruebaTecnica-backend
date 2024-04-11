package com.prueba.consultorioMedico.service;

import com.prueba.consultorioMedico.model.MedicalAppointment;
import com.prueba.consultorioMedico.model.Patient;
import com.prueba.consultorioMedico.model.Professional;
import com.prueba.consultorioMedico.model.Speciality;

import java.time.LocalDateTime;
import java.util.List;

public interface IMedicalAppointmentService {
    List<MedicalAppointment> findAllByPatient(Patient patient);
    List<MedicalAppointment> findAllByProfessional(Professional professional);
    List<MedicalAppointment> findAllBySpeciality(Speciality speciality);
    List<MedicalAppointment> findAll();
    void addAppointment(String patientDni, String professionalDni, String consultingRoomName, LocalDateTime date);
    void deleteAppointment(Long appointmentId);
    void updateAppointment(Long appointmentId,String patientDni, String professionalDni,
                           String consultingRoomName, LocalDateTime date);

}
