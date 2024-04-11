package com.prueba.consultorioMedico.service.impl;

import com.prueba.consultorioMedico.dto.MedicalAppointmentDto;
import com.prueba.consultorioMedico.model.MedicalAppointment;
import com.prueba.consultorioMedico.model.Patient;
import com.prueba.consultorioMedico.model.Professional;
import com.prueba.consultorioMedico.model.Speciality;
import com.prueba.consultorioMedico.repository.IMedicalAppointmentRepository;
import com.prueba.consultorioMedico.service.IMedicalAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalAppointmentService implements IMedicalAppointmentService {

    @Autowired
    private IMedicalAppointmentRepository medicalRepository;

    @Override
    public List<MedicalAppointmentDto> findAllByPatient(Patient patient) {
        List<MedicalAppointment> medicalAppointmentList = medicalRepository.findAllByPatient(patient);
        return formatData(medicalAppointmentList);
    }

    @Override
    public List<MedicalAppointmentDto> findAllByProfessional(Professional professional) {
        List<MedicalAppointment> medicalAppointmentList = medicalRepository.findAllByProfessional(professional);
        return formatData(medicalAppointmentList);
    }

    @Override
    public List<MedicalAppointmentDto> findAllBySpeciality(Speciality speciality) {
        List<MedicalAppointment> medicalAppointmentList = medicalRepository.findAllBySpeciality(speciality);
        return formatData(medicalAppointmentList);
    }

    @Override
    public List<MedicalAppointmentDto> findAll() {
        List<MedicalAppointment> medicalAppointmentList = medicalRepository.findAll();
        return formatData(medicalAppointmentList);
    }

    @Override
    public void add(MedicalAppointmentDto medicalAppointmentDto) {

    }

    @Override
    public void deleteAppointment(Long appointmentId) {

    }

    @Override
    public void updateAppointment(MedicalAppointmentDto medicalAppointmentDto) {

    }

    //Aux
    public List<MedicalAppointmentDto> formatData(List<MedicalAppointment> medicalAppointmentList){
        List<MedicalAppointmentDto> medicalAppointmentDtoList = new ArrayList<>();

        medicalAppointmentList.forEach((medicalAppointment -> {
            MedicalAppointmentDto dto = MedicalAppointmentDto.builder()
                    .professionalDni(medicalAppointment.getProfessional().getDni())
                    .professionalName(medicalAppointment.getProfessional().getName())
                    .professionalLastname(medicalAppointment.getProfessional().getLastname())
                    .patientDni(medicalAppointment.getPatient().getDni())
                    .patientName(medicalAppointment.getPatient().getName())
                    .patientLastName(medicalAppointment.getPatient().getLastname())
                    .consultingRoomName(medicalAppointment.getConsultingRoom().getConsultingRoomName())
                    .date(medicalAppointment.getAppointmentDate())
                    .build();
            medicalAppointmentDtoList.add(dto);
        }));
        return medicalAppointmentDtoList;
    }
}
