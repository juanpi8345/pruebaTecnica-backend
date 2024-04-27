package com.prueba.consultorioMedico.repository;

import com.prueba.consultorioMedico.Util;
import com.prueba.consultorioMedico.model.MedicalAppointment;
import com.prueba.consultorioMedico.model.Professional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MedicalAppointmentRepositoryTest {

    @Autowired
    private  IMedicalAppointmentRepository medicalAppointmentRepository;

    @Autowired
    private  IPatientRepository patientRepository;

    @Autowired
    private  IProfessionalRepository professionalRepository;

    @Autowired
    private  IConsultingRoomRepository consultingRoomRepository;

    @Autowired
    private ISpecialityRepository specialityRepository;

    @BeforeEach
      void saveEntities(){
        specialityRepository.save(Util.speciality);
        patientRepository.save(Util.patient);
        patientRepository.save(Util.patient2);
        professionalRepository.save(Util.professional);
        professionalRepository.save(Util.professional2);
        consultingRoomRepository.save(Util.consultingRoom);
        Util.addMedicalAppointments();
        medicalAppointmentRepository.save(Util.medicalAppointments.get(0));
        medicalAppointmentRepository.save(Util.medicalAppointments.get(1));
        medicalAppointmentRepository.save(Util.medicalAppointments.get(2));
    }

    @Test
    @Rollback
    @Order(1)
    void findAllByPatient(){
        List<MedicalAppointment> medicalAppointmentList = medicalAppointmentRepository.findAllByPatient(Util.patient);
        assertNotNull(medicalAppointmentList);
        assertEquals("Juan",medicalAppointmentList.get(0).getPatient().getName());
        assertEquals(2,medicalAppointmentList.size());
    }

    @Test
    @Rollback
    @Order(2)
    void findAllByProfessional(){
        List<MedicalAppointment> medicalAppointmentList = medicalAppointmentRepository.findAllByProfessional(Util.professional2);
        assertAll("Professionals",
                ()->assertNotNull(medicalAppointmentList),
                ()->assertEquals(1,medicalAppointmentList.size()));
    }

    @Test
    @Rollback
    @Order(3)
    void findAllByProfessionalNotFound(){
        List<MedicalAppointment> medicalAppointmentList = medicalAppointmentRepository.findAllByProfessional(
                Professional.builder().dni("10000000").build()
        );
        assertEquals(0,medicalAppointmentList.size());
    }

    @Test
    @Rollback
    @Order(4)
    void findAllBySpeciality(){
        List<MedicalAppointment> medicalAppointmentList = medicalAppointmentRepository.findAllBySpeciality(Util.speciality);
        assertEquals(3,medicalAppointmentList.size());
    }
}
