package com.meditrack.patient.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentRequest {
    private Long patientId;
    private LocalDate appointmentDate;
    private String comments;
    private Boolean isPresent;
}
