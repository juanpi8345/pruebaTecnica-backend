package com.prueba.consultorioMedico.service;

import com.prueba.consultorioMedico.Util;
import com.prueba.consultorioMedico.dto.FullMedicalAppointmentDto;
import com.prueba.consultorioMedico.model.MedicalAppointment;
import com.prueba.consultorioMedico.repository.IMedicalAppointmentRepository;
import com.prueba.consultorioMedico.repository.IPatientRepository;
import com.prueba.consultorioMedico.repository.IProfessionalRepository;
import com.prueba.consultorioMedico.repository.ISpecialityRepository;
import com.prueba.consultorioMedico.service.impl.MedicalAppointmentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MedicalAppointmentServiceTest {

    @Mock
    private IMedicalAppointmentRepository medicalAppointmentRepository;

    @Mock
    private IProfessionalRepository professionalRepository;

    @Mock
    private IPatientRepository patientRepository;

    @Mock
    private ISpecialityRepository specialityRepository;

    @InjectMocks
    private MedicalAppointmentService medicalAppointmentService;

    @BeforeAll
    static void setup(){
        Util.addMedicalAppointments();
    }

    @Test
    @Order(1)
    void findByProfessional(){
        List<MedicalAppointment> medicalAppointmentList = new ArrayList<>();
        medicalAppointmentList.add(Util.medicalAppointments.get(0));
        medicalAppointmentList.add(Util.medicalAppointments.get(1));
        given(professionalRepository.findById(Util.professional.getDni())).willReturn(Optional.of(Util.professional));
        given(medicalAppointmentRepository.findAllByProfessional(Util.professional))
                .willReturn(medicalAppointmentList);

        List<FullMedicalAppointmentDto> medicalAppointments = medicalAppointmentService.findAllByProfessional(Util.professional.getDni());
        assertNotNull(medicalAppointments);
        assertEquals(2,medicalAppointments.size());
    }

    @Test
    @Order(2)
    void findByPatient(){
        List<MedicalAppointment> medicalAppointmentList = new ArrayList<>();
        medicalAppointmentList.add(Util.medicalAppointments.get(0));
        medicalAppointmentList.add(Util.medicalAppointments.get(1));
        given(patientRepository.findById(Util.patient.getDni())).willReturn(Optional.of(Util.patient));
        given(medicalAppointmentRepository.findAllByPatient(Util.patient)).willReturn(medicalAppointmentList);

        List<FullMedicalAppointmentDto> medicalAppointments = medicalAppointmentService.findAllByPatient(Util.patient.getDni());
        assertNotNull(medicalAppointments);
        assertEquals(2,medicalAppointments.size());
    }

    @Test
    @Order(3)
    void findBySpeciality(){
        List<MedicalAppointment> medicalAppointmentList = new ArrayList<>();
        medicalAppointmentList.add(Util.medicalAppointments.get(0));
        medicalAppointmentList.add(Util.medicalAppointments.get(1));
        medicalAppointmentList.add(Util.medicalAppointments.get(2));
        given(specialityRepository.findById(Util.speciality.getName())).willReturn(Optional.of(Util.speciality));
        given(medicalAppointmentRepository.findAllBySpeciality(Util.speciality)).willReturn(medicalAppointmentList);

        List<FullMedicalAppointmentDto> medicalAppointments = medicalAppointmentService.findAllBySpeciality(Util.speciality.getName());
        assertNotNull(medicalAppointments);
        assertEquals(3,medicalAppointments.size());
    }
}
