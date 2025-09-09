package com.meditrack.patient.domain;

import lombok.Data;

@Data
public class PatientAndAppointment {
    private Long patientId;
    private Long appointmentId;
}
