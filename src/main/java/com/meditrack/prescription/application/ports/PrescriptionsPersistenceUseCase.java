package com.meditrack.prescription.application.ports;

import com.meditrack.prescription.domain.UpdatePrescriptionRequest;

public interface PrescriptionsPersistenceUseCase {

    Long createPrescription(Long patientId, Long appointmentId, Long doctorId);

    void updatePrescription(UpdatePrescriptionRequest updatePrescriptionRequest);
}
