package com.meditrack.prescription.domain;

import lombok.Data;

@Data
public class PrescriptionCreationRequest {

    private Long appointmentId;
    private Long patientId;
}
