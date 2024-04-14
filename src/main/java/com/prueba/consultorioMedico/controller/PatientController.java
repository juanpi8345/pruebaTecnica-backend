package com.prueba.consultorioMedico.controller;

import com.prueba.consultorioMedico.dto.Message;
import com.prueba.consultorioMedico.model.Patient;
import com.prueba.consultorioMedico.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
//Recomendable poner la url del cliente frontend
@CrossOrigin("*")
public class PatientController {

    @Autowired
    private IPatientService patientService;

    @GetMapping("/get")
    public ResponseEntity<List<Patient>> findAll(){
        return ResponseEntity.ok(patientService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Message> addPatient(@RequestBody Patient patient){
        patientService.add(patient);
        Message message = Message.builder().status(HttpStatus.OK).message("Paciente guardado correctamente").build();
        return ResponseEntity.ok(message);
    }
}
