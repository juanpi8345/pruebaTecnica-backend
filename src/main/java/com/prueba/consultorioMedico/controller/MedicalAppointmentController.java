package com.prueba.consultorioMedico.controller;

import com.prueba.consultorioMedico.dto.FullMedicalAppointmentDto;
import com.prueba.consultorioMedico.service.IMedicalAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
//Recomendable poner la url del cliente frontend
@CrossOrigin("*")
public class MedicalAppointmentController {
    @Autowired
    private IMedicalAppointmentService medicalAppointmentService;

    @GetMapping("/get")
    public ResponseEntity<List<FullMedicalAppointmentDto>> findAll(){
        return ResponseEntity.ok(medicalAppointmentService.findAll());
    }

}
