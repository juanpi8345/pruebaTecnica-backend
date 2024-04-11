package com.prueba.consultorioMedico.util;

import com.prueba.consultorioMedico.exception.OutOfServiceException;
import com.prueba.consultorioMedico.exception.OutOfTimeException;

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

    public static void validateAbleToModifyOrDelete(LocalDateTime appointmentTime) throws OutOfTimeException{
        LocalDateTime now = LocalDateTime.now();
        //Si el turno es antes que el dia y horario actual + 1 hora.
        if(appointmentTime.isBefore(now.plusHours(1)) ){
            throw new OutOfTimeException("Error no se puede modificar o eliminar el turno dado a que falta menos de una hora para el mismo");
        }
    }



}
