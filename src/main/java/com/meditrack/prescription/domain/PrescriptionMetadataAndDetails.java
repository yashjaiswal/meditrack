package com.meditrack.prescription.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PrescriptionMetadataAndDetails {
    private Long patientId;
    private Long doctorId;
    private Long appointmentId;
    private LocalDateTime prescriptionDate;
    PrescriptionDetails prescriptionDetails;
}