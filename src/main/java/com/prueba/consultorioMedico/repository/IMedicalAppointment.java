package com.prueba.consultorioMedico.repository;

import com.prueba.consultorioMedico.model.MedicalAppointment;
import com.prueba.consultorioMedico.model.Patient;
import com.prueba.consultorioMedico.model.Professional;
import com.prueba.consultorioMedico.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMedicalAppointment extends JpaRepository<MedicalAppointment,Long> {
    List<MedicalAppointment> findAllByPatient(Patient patient);
    List<MedicalAppointment> findAllByProfessional(Professional professional);
    @Query("SELECT ma FROM MedicalAppointment ma WHERE :speciality MEMBER OF ma.professional.specialityList")
    List<MedicalAppointment> findAllBySpeciality(@Param("speciality") Speciality speciality);
}
