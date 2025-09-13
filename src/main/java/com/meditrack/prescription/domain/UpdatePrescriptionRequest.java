package com.meditrack.prescription.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UpdatePrescriptionRequest {

    private Long patientId;

    private Long prescriptionId;

    private List<PrescriptionDosage> prescriptionDosages;

    private LocalDate nextAppointmentDate;

    private String commentsForNextAppointment;

}
