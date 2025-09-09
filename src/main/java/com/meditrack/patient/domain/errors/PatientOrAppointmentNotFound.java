package com.meditrack.patient.domain.errors;

public class PatientOrAppointmentNotFound extends RuntimeException {
    public PatientOrAppointmentNotFound(String message) {
        super(message);
    }
}
