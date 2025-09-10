package com.meditrack.prescription.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UpdatePrescriptionRequest {

    private Long patientId;

    private Long prescriptionId;

    private List<Dosage> dosages;

    private LocalDate nextAppointmentDate;

    private String commentsForNextAppointment;

}
