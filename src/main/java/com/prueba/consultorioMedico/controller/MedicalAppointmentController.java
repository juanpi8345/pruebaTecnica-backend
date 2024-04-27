package com.prueba.consultorioMedico.controller;

import com.prueba.consultorioMedico.dto.FullMedicalAppointmentDto;
import com.prueba.consultorioMedico.dto.Message;
import com.prueba.consultorioMedico.dto.SimpleMedicalAppointmentDto;
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

    /*Este metodo lo voy a usar para cuando el front vaya a la pagina de actualizacion
      Poder obtener los datos y autocompletar el formulario, por eso es que retorno
      el dto simple. */
    @GetMapping("/get/{appointmentId}")
    public ResponseEntity<SimpleMedicalAppointmentDto> findById(@PathVariable Long appointmentId){
        return ResponseEntity.ok(medicalAppointmentService.findById(appointmentId));
    }

    @GetMapping("/get/patient/{patientDni}")
    public ResponseEntity<List<FullMedicalAppointmentDto>> findByPatient(@PathVariable String patientDni){
        return ResponseEntity.ok(medicalAppointmentService.findAllByPatient(patientDni));
    }

    @GetMapping("/get/professional/{professionalDni}")
    public ResponseEntity<List<FullMedicalAppointmentDto>> findByProfessional(@PathVariable String professionalDni){
        return ResponseEntity.ok(medicalAppointmentService.findAllByProfessional(professionalDni));
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

    @PutMapping("/update/{medicalAppointmentId}")
    public ResponseEntity<Message> updateAppointment(@PathVariable Long medicalAppointmentId,
                                                     @RequestBody SimpleMedicalAppointmentDto simpleMedicalAppointmentDto){
        medicalAppointmentService.updateAppointment(medicalAppointmentId,simpleMedicalAppointmentDto);
        Message message = Message.builder().status(HttpStatus.OK).message("Cita modificada correctamente").build();
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{medicalAppointmentId}")
    public ResponseEntity<Message> deleteAppointment(@PathVariable Long medicalAppointmentId){
        medicalAppointmentService.deleteAppointment(medicalAppointmentId);
        Message message = Message.builder().status(HttpStatus.OK).message("Cita eliminada correctamente").build();
        return ResponseEntity.ok(message);
    }

}
