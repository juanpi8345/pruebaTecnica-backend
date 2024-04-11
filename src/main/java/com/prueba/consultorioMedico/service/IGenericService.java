package com.prueba.consultorioMedico.service;

import com.prueba.consultorioMedico.model.Speciality;

import java.util.List;
                                //Entity
public interface IGenericService<E> {
    List<E> findAll();
    void add(E e);
}
