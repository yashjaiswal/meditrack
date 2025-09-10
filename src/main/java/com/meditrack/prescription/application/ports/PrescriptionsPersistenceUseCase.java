package com.meditrack.prescription.application.ports;

public interface PrescriptionsPersistenceUseCase {

    Long createPrescription(Long patientId, Long appointmentId, Long doctorId);
}
