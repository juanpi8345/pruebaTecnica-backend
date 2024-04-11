package com.prueba.consultorioMedico.controller;

import com.prueba.consultorioMedico.dto.FullMedicalAppointmentDto;
import com.prueba.consultorioMedico.dto.Message;
import com.prueba.consultorioMedico.dto.PatientDto;
import com.prueba.consultorioMedico.dto.SimpleMedicalAppointmentDto;
import com.prueba.consultorioMedico.model.Patient;
import com.prueba.consultorioMedico.service.IMedicalAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/get/patient/{patientName}")
    public ResponseEntity<List<FullMedicalAppointmentDto>> findByPatient(@PathVariable String patientName){
        return ResponseEntity.ok(medicalAppointmentService.findAllByPatient(patientName));
    }

    @GetMapping("/get/professional/{professionalName}")
    public ResponseEntity<List<FullMedicalAppointmentDto>> findByProfessional(@PathVariable String professionalName){
        return ResponseEntity.ok(medicalAppointmentService.findAllByProfessional(professionalName));
    }

    @GetMapping("/get/speciality/{specialityName}")
    public ResponseEntity<List<FullMedicalAppointmentDto>> findBySpeciality(@PathVariable String specialityName){
        return ResponseEntity.ok(medicalAppointmentService.findAllBySpeciality(specialityName));
    }

    @PostMapping("/add")
    public ResponseEntity<Message> addAppointment(@RequestBody SimpleMedicalAppointmentDto simpleMedicalAppointmentDto){
        medicalAppointmentService.add(simpleMedicalAppointmentDto);
        Message message = Message.builder().status(HttpStatus.OK).message("Cita guardada correctamente").build();
        return ResponseEntity.ok(message);
    }

}
