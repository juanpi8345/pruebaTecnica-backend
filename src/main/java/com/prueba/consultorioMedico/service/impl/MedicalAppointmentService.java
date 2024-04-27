package com.prueba.consultorioMedico.service.impl;

import com.prueba.consultorioMedico.dto.FullMedicalAppointmentDto;
import com.prueba.consultorioMedico.dto.SimpleMedicalAppointmentDto;
import com.prueba.consultorioMedico.model.*;
import com.prueba.consultorioMedico.repository.*;
import com.prueba.consultorioMedico.service.IMedicalAppointmentService;
import com.prueba.consultorioMedico.util.DateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public SimpleMedicalAppointmentDto findById(Long medicalAppointmentId) {
        MedicalAppointment medicalAppointment = medicalAppointmentRepository.findById(medicalAppointmentId).orElseThrow();
        //Obtengo las entidades
        Professional professional = professionalRepository.findById(medicalAppointment.getProfessional().getDni()).orElseThrow();
        Speciality speciality = specialityRepository.findById(medicalAppointment.getSpeciality().getName()).orElseThrow();
        Patient patient = patientRepository.findById(medicalAppointment.getPatient().getDni()).orElseThrow();
        ConsultingRoom consultingRoom = consultingRoomRepository.findById(medicalAppointment.getConsultingRoom().getConsultingRoomName()).orElseThrow();
        //Preparo la respuesta y retorno
        return SimpleMedicalAppointmentDto.builder()
                .professionalDni(professional.getDni())
                .specialityName(speciality.getName())
                .patientDni(patient.getDni())
                .consultingRoomName(consultingRoom.getConsultingRoomName())
                .date(medicalAppointment.getAppointmentDate()).build();
    }

    @Override
    public List<FullMedicalAppointmentDto> findAllByPatient(String patientDni) {
        Patient patient = patientRepository.findById(patientDni).orElseThrow();
        List<MedicalAppointment> medicalAppointmentList = medicalAppointmentRepository.findAllByPatient(patient);
        return this.formatData(medicalAppointmentList);
    }

    @Override
    public List<FullMedicalAppointmentDto> findAllByProfessional(String professionalDni) {
        Professional professional = professionalRepository.findById(professionalDni).orElseThrow();
        List<MedicalAppointment> medicalAppointmentList = medicalAppointmentRepository.findAllByProfessional(professional);
        return this.formatData(medicalAppointmentList);
    }

    @Override
    public List<FullMedicalAppointmentDto> findAllBySpeciality(String specialityName) {
        Speciality speciality = specialityRepository.findById(specialityName).orElseThrow();
        List<MedicalAppointment> medicalAppointmentList = medicalAppointmentRepository.findAllBySpeciality(speciality);
        return this.formatData(medicalAppointmentList);
    }

    @Override
    public List<FullMedicalAppointmentDto> findAll() {
        List<MedicalAppointment> medicalAppointmentList = medicalAppointmentRepository.findAll();
        return this.formatData(medicalAppointmentList);
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
                                                professional.getStart(), professional.getFinish());

        //Verifico que las entidades existan
        Patient patient = patientRepository.findById(medicalAppointmentDto.getPatientDni()).orElseThrow();
        ConsultingRoom consultingRoom = consultingRoomRepository.findById(medicalAppointmentDto.getConsultingRoomName()).orElseThrow();
        Speciality speciality = specialityRepository.findById(medicalAppointmentDto.getSpecialityName()).orElseThrow();

        //Armo el objeto que se guardara en la bd
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
    public void updateAppointment(Long appointmentId, SimpleMedicalAppointmentDto simpleMedicalAppointmentDto) {
        MedicalAppointment medicalAppointment = medicalAppointmentRepository.findById(appointmentId).orElseThrow();
        //Verificar si el turno se puede modificar
        DateValidation.validateAbleToModifyOrDelete(simpleMedicalAppointmentDto.getDate());
        //Verificar que el nuevo turno cumpla con las restricciones
        DateValidation.validateAppointmentTime(simpleMedicalAppointmentDto.getDate().toLocalTime());
        DateValidation.validateDayOfWeek(simpleMedicalAppointmentDto.getDate());
        //Verificar que las entidades existan
        Professional professional = professionalRepository.findById(simpleMedicalAppointmentDto.getProfessionalDni()).orElseThrow();
        //Verificar que el profesional pueda atender el nuevo turno
        DateValidation.validateProfessionalTime(simpleMedicalAppointmentDto.getDate().toLocalTime(),professional.getStart(),
                professional.getFinish());
        Speciality speciality = specialityRepository.findById(simpleMedicalAppointmentDto.getSpecialityName()).orElseThrow();
        Patient patient = patientRepository.findById(simpleMedicalAppointmentDto.getPatientDni()).orElseThrow();
        ConsultingRoom consultingRoom = consultingRoomRepository.findById(simpleMedicalAppointmentDto.getConsultingRoomName()).orElseThrow();

        //Seteo el id asi se remplaza
        medicalAppointment.setMedicalAppointmentId(appointmentId);
        medicalAppointment.setPatient(patient);
        medicalAppointment.setProfessional(professional);
        medicalAppointment.setSpeciality(speciality);
        medicalAppointment.setConsultingRoom(consultingRoom);
        medicalAppointment.setAppointmentDate(simpleMedicalAppointmentDto.getDate());
        medicalAppointmentRepository.save(medicalAppointment);
    }

    @Override
    public void cancel(Long medicalAppointmentId) {
        //Para comprobar que exista
        MedicalAppointment medicalAppointment = medicalAppointmentRepository.findById(medicalAppointmentId).orElseThrow();
        //Verifico si se puede eliminar
        DateValidation.validateAbleToModifyOrDelete(medicalAppointment.getAppointmentDate());
        medicalAppointmentRepository.deleteById(medicalAppointmentId);
    }

    //Aux
    //Lo que hago en este metodo es formatear la informacion de la lista de turnos
    //La diferencia es que en la lista de turnos normal tiene objetos como profesionales, pacientes, especialidades
    //Para hacerlo mas simple itero cada turno, desarmo sus objetos y lo guardo en la lista de dtos.
    //Al terminar retorno la lista con todos los dto
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
    //Hago este metodo porque le metodo generico me pide un fullDto
    //Para el usuario seria tedioso llenar toda esa informacion
    //Lo que hago en este metodo es construir el dto full y llamar al otro metodo add
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

}
