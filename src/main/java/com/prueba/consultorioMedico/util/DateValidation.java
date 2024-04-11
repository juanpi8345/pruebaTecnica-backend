package com.prueba.consultorioMedico.util;

import com.prueba.consultorioMedico.exception.OutOfServiceException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateValidation {

    public static void validateDayOfWeek(LocalDateTime date) throws OutOfServiceException{
        if(date.getDayOfWeek() == DayOfWeek.SUNDAY)
            throw new OutOfServiceException("Error, la clinica no otorga turnos los dias domingo");
    }

    public static void validateAppointmentTime(LocalTime date) throws OutOfServiceException{
        if(date.isBefore(LocalTime.of(8,0)) || date.isAfter(LocalTime.of(23,0)))
            throw new OutOfServiceException("Error, la clinica atienda de 8hs a 23hs");
    }

    public static void validateProfessionalTime(LocalTime appointmentTime,
                                                LocalTime professionalStartTime,
                                                LocalTime professionalEndTime) throws OutOfServiceException{
        if(appointmentTime.isBefore( professionalStartTime) || appointmentTime.isAfter(professionalEndTime))
            throw new OutOfServiceException("Error, el profesional no esta disponible a las "+ appointmentTime+ "hs");
    }



}
