package com.prueba.consultorioMedico.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.consultorioMedico.Util;
import com.prueba.consultorioMedico.dto.FullMedicalAppointmentDto;
import com.prueba.consultorioMedico.model.MedicalAppointment;
import com.prueba.consultorioMedico.service.impl.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
public class MedicalAppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalAppointmentService medicalAppointmentService;

    @MockBean
    private ConsultingRoomService consultingRoomService;

    @MockBean
    private PatientService patientService;

    @MockBean
    private SpecialityService specialityService;

    @MockBean
    private ProfessionalService professionalService;

    @Autowired
    private ObjectMapper objectMapper;

    private static String url;

    @BeforeAll
    static void setup(){
        Util.addMedicalAppointments();
        url = "/api/appointment/";
    }

    @Test
    void findByProfessional() throws Exception {
        List<MedicalAppointment> medicalAppointmentList = new ArrayList<>();
        medicalAppointmentList.add(Util.medicalAppointments.get(0));
        medicalAppointmentList.add(Util.medicalAppointments.get(1));
        List<FullMedicalAppointmentDto> medicalAppointmentDtoList = Util.formatList(medicalAppointmentList);
        given(medicalAppointmentService.findAllByProfessional(Util.professional.getDni()))
                .willReturn(medicalAppointmentDtoList);
        ResultActions response = mockMvc.perform(get(url+"get/professional/{professionalDni}"
                                                ,Util.professional.getDni()));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void findBySpeciality() throws Exception {
        List<MedicalAppointment> medicalAppointmentList = new ArrayList<>();
        medicalAppointmentList.add(Util.medicalAppointments.get(0));
        medicalAppointmentList.add(Util.medicalAppointments.get(1));
        medicalAppointmentList.add(Util.medicalAppointments.get(2));
        List<FullMedicalAppointmentDto> medicalAppointmentDtoList = Util.formatList(medicalAppointmentList);
        given(medicalAppointmentService.findAllBySpeciality(Util.speciality.getName()))
                .willReturn(medicalAppointmentDtoList);
        ResultActions response = mockMvc.perform(get(url+"get/speciality/{specialityName}"
                ,Util.speciality.getName()));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3));
    }

    @Test
    void findByPatient() throws Exception {
        List<MedicalAppointment> medicalAppointmentList = new ArrayList<>();
        medicalAppointmentList.add(Util.medicalAppointments.get(2));
        List<FullMedicalAppointmentDto> medicalAppointmentDtoList = Util.formatList(medicalAppointmentList);
        given(medicalAppointmentService.findAllByPatient(Util.patient.getDni()))
                .willReturn(medicalAppointmentDtoList);
        ResultActions response = mockMvc.perform(get(url+"get/patient/{patientDni}"
                ,Util.patient.getDni()));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }
}
