package com.prueba.consultorioMedico.service;

import com.prueba.consultorioMedico.model.Professional;
import com.prueba.consultorioMedico.model.Speciality;

import java.util.Optional;

public interface IProfessionalService extends IGenericService<Professional> {
    void addSpeciality(String professionalDni, String specialityName);
}
