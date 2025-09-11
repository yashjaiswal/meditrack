package com.meditrack.prescription.application.ports;

import com.meditrack.prescription.domain.PrescriptionCreationRequest;
import com.meditrack.prescription.domain.UpdatePrescriptionRequest;
import org.springframework.http.ResponseEntity;

public interface PrescriptionsUseCase {

    Long createPrescription(Long doctorId, PrescriptionCreationRequest prescriptionCreationRequest);

    void updatePrescription(UpdatePrescriptionRequest updatePrescriptionRequest);

    ResponseEntity<byte[]> generatePrescriptionPDF(Long prescriptionId);
}
