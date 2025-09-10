package com.meditrack.prescription.application.ports;

import com.meditrack.prescription.domain.PrescriptionCreationRequest;

public interface PrescriptionsUseCase {

    Long createPrescription(Long doctorId, PrescriptionCreationRequest prescriptionCreationRequest);
}
