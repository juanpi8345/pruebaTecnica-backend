package com.prueba.consultorioMedico.service.impl;

import com.prueba.consultorioMedico.dto.FullMedicalAppointmentDto;
import com.prueba.consultorioMedico.dto.SimpleMedicalAppointmentDto;
import com.prueba.consultorioMedico.model.*;
import com.prueba.consultorioMedico.repository.*;
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

    @Autowired
    private ISpecialityRepository specialityRepository;

    @Override
    public List<FullMedicalAppointmentDto> findAllByPatient(String patientDni) {
        Patient patient = patientRepository.findById(patientDni).orElseThrow();
        List<MedicalAppointment> medicalAppointmentList = medicalAppointmentRepository.findAllByPatient(patient);
        return formatData(medicalAppointmentList);
    }

    @Override
    public List<FullMedicalAppointmentDto> findAllByProfessional(String professionalDni) {
        Professional professional = professionalRepository.findById(professionalDni).orElseThrow();
        List<MedicalAppointment> medicalAppointmentList = medicalAppointmentRepository.findAllByProfessional(professional);
        return formatData(medicalAppointmentList);
    }

    @Override
    public List<FullMedicalAppointmentDto> findAllBySpeciality(String specialityName) {
        Speciality speciality = specialityRepository.findById(specialityName).orElseThrow();
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
        Speciality speciality = specialityRepository.findById(medicalAppointmentDto.getSpecialityName()).orElseThrow();

        MedicalAppointment medicalAppointment = MedicalAppointment.builder()
                .appointmentDate(medicalAppointmentDto.getDate())
                .patient(patient).professional(professional).consultingRoom(consultingRoom).
                speciality(speciality).build();

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
                    .medicalAppointmentId(medicalAppointment.getMedicalAppointmentId())
                    .professionalDni(medicalAppointment.getProfessional().getDni())
                    .professionalName(medicalAppointment.getProfessional().getName())
                    .professionalLastname(medicalAppointment.getProfessional().getLastname())
                    .patientDni(medicalAppointment.getPatient().getDni())
                    .patientName(medicalAppointment.getPatient().getName())
                    .patientLastname(medicalAppointment.getPatient().getLastname())
                    .specialityName(medicalAppointment.getSpeciality().getName())
                    .consultingRoomName(medicalAppointment.getConsultingRoom().getConsultingRoomName())
                    .date(medicalAppointment.getAppointmentDate())
                    .build();
            medicalAppointmentDtoList.add(dto);
        }));
        return medicalAppointmentDtoList;
    }

    //Aux
    @Override
    public void add(SimpleMedicalAppointmentDto simpleMedicalAppointmentDto) {
        Professional professional = professionalRepository.findById(simpleMedicalAppointmentDto.getProfessionalDni())
                .orElseThrow();
        Patient patient = patientRepository.findById(simpleMedicalAppointmentDto.getPatientDni())
                .orElseThrow();

        FullMedicalAppointmentDto fullMedicalAppointmentDto = FullMedicalAppointmentDto.builder()
                .professionalDni(professional.getDni()).professionalName(professional.getName())
                .professionalLastname(professional.getLastname())
                .patientDni(patient.getDni()).patientName(patient.getName())
                .patientLastname(patient.getLastname())
                .consultingRoomName(simpleMedicalAppointmentDto.getConsultingRoomName())
                .specialityName(simpleMedicalAppointmentDto.getSpecialityName())
                .date(simpleMedicalAppointmentDto.getDate()).build();
        //Una vez ya formateado llamo al otro metodo add de este servicio, donde ahi se guardara la cita
        this.add(fullMedicalAppointmentDto);
    }

    @Override
    public void cancel(Long medicalAppointmentId) {
        //Para comprobar que exista
        MedicalAppointment medicalAppointment = medicalAppointmentRepository.findById(medicalAppointmentId).orElseThrow();
        DateValidation.validateAbleToModifyOrDelete(medicalAppointment.getAppointmentDate());
        medicalAppointmentRepository.deleteById(medicalAppointmentId);
    }
}
