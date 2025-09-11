package com.meditrack.prescription.application.ports;

import com.meditrack.prescription.domain.PrescriptionCreationRequest;
import com.meditrack.prescription.domain.UpdatePrescriptionRequest;

public interface PrescriptionsUseCase {

    Long createPrescription(Long doctorId, PrescriptionCreationRequest prescriptionCreationRequest);

    void updatePrescription(UpdatePrescriptionRequest updatePrescriptionRequest);
}
