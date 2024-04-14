package com.prueba.consultorioMedico.service;

import com.prueba.consultorioMedico.model.Professional;
import com.prueba.consultorioMedico.model.Speciality;

import java.util.List;
import java.util.Optional;

public interface ISpecialityService extends IGenericService<Speciality> {
    List<Speciality> findSpecialitiesByProfessional(String professionalDni);
}
