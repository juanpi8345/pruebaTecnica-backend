package com.prueba.consultorioMedico.service.impl;

import com.prueba.consultorioMedico.dto.FullMedicalAppointmentDto;
import com.prueba.consultorioMedico.model.*;
import com.prueba.consultorioMedico.repository.IConsultingRoomRepository;
import com.prueba.consultorioMedico.repository.IMedicalAppointmentRepository;
import com.prueba.consultorioMedico.repository.IPatientRepository;
import com.prueba.consultorioMedico.repository.IProfessionalRepository;
import com.prueba.consultorioMedico.service.IMedicalAppointmentService;
import com.prueba.consultorioMedico.util.DateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalAppointmentService implements IMedicalAppointmentService {

    @Autowired
    private IMedicalAppointmentRepository medicalAppointmentRepository;

    @Autowired
    private IProfessionalRepository professionalRepository;

    @Autowired
    private IConsultingRoomRepository consultingRoomRepository;

    @Autowired
    private IPatientRepository patientRepository;

    @Override
    public List<FullMedicalAppointmentDto> findAllByPatient(Patient patient) {
        List<MedicalAppointment> medicalAppointmentList = medicalAppointmentRepository.findAllByPatient(patient);
        return formatData(medicalAppointmentList);
    }

    @Override
    public List<FullMedicalAppointmentDto> findAllByProfessional(Professional professional) {
        List<MedicalAppointment> medicalAppointmentList = medicalAppointmentRepository.findAllByProfessional(professional);
        return formatData(medicalAppointmentList);
    }

    @Override
    public List<FullMedicalAppointmentDto> findAllBySpeciality(Speciality speciality) {
        List<MedicalAppointment> medicalAppointmentList = medicalAppointmentRepository.findAllBySpeciality(speciality);
        return formatData(medicalAppointmentList);
    }

    @Override
    public List<FullMedicalAppointmentDto> findAll() {
        List<MedicalAppointment> medicalAppointmentList = medicalAppointmentRepository.findAll();
        return formatData(medicalAppointmentList);
    }

    @Override
    public void add(FullMedicalAppointmentDto medicalAppointmentDto){
        //Verificar si la fecha no es domingo
        DateValidation.validateDayOfWeek(medicalAppointmentDto.getDate());
        //Verificar si el horario no esta fuera de servicio
        DateValidation.validateAppointmentTime(medicalAppointmentDto.getDate().toLocalTime());
        Professional professional = professionalRepository.findById(medicalAppointmentDto.getProfessionalDni()).orElseThrow();
        //Verificar si el profesional esta disponible
        DateValidation.validateProfessionalTime(medicalAppointmentDto.getDate().toLocalTime(),
                                                professional.getStart(), professional.getEnd());

        Patient patient = patientRepository.findById(medicalAppointmentDto.getPatientDni()).orElseThrow();
        ConsultingRoom consultingRoom = consultingRoomRepository.findById(medicalAppointmentDto.getConsultingRoomName()).orElseThrow();

        MedicalAppointment medicalAppointment = MedicalAppointment.builder()
                .appointmentDate(medicalAppointmentDto.getDate())
                .patient(patient).professional(professional).consultingRoom(consultingRoom).build();

        patient.getMedicalAppointments().add(medicalAppointment);
        patientRepository.save(patient);
        professional.getMedicalAppointments().add(medicalAppointment);
        professionalRepository.save(professional);
        medicalAppointmentRepository.save(medicalAppointment);
    }

    @Override
    public void deleteAppointment(Long appointmentId) {
        MedicalAppointment medicalAppointment = medicalAppointmentRepository.findById(appointmentId).orElseThrow();
        //Verificar si el turno se puede eliminar en base a la fecha y hora
        DateValidation.validateAbleToModifyOrDelete(medicalAppointment.getAppointmentDate());
        medicalAppointmentRepository.deleteById(appointmentId);
    }

    @Override
    public void updateAppointment(Long appointmentId,LocalDateTime newDate) {
        MedicalAppointment medicalAppointment = medicalAppointmentRepository.findById(appointmentId).orElseThrow();
        //Verificar si el turno se puede modificar
        DateValidation.validateAbleToModifyOrDelete(medicalAppointment.getAppointmentDate());
        medicalAppointment.setAppointmentDate(newDate);
        medicalAppointmentRepository.save(medicalAppointment);
    }

    //Aux
    public List<FullMedicalAppointmentDto> formatData(List<MedicalAppointment> medicalAppointmentList){
        List<FullMedicalAppointmentDto> medicalAppointmentDtoList = new ArrayList<>();

        medicalAppointmentList.forEach((medicalAppointment -> {
            FullMedicalAppointmentDto dto = FullMedicalAppointmentDto.builder()
                    .professionalDni(medicalAppointment.getProfessional().getDni())
                    .professionalName(medicalAppointment.getProfessional().getName())
                    .professionalLastname(medicalAppointment.getProfessional().getLastname())
                    .patientDni(medicalAppointment.getPatient().getDni())
                    .patientName(medicalAppointment.getPatient().getName())
                    .patientLastname(medicalAppointment.getPatient().getLastname())
                    .consultingRoomName(medicalAppointment.getConsultingRoom().getConsultingRoomName())
                    .date(medicalAppointment.getAppointmentDate())
                    .build();
            medicalAppointmentDtoList.add(dto);
        }));
        return medicalAppointmentDtoList;
    }
}
