package com.meditrack.prescription.application.service;

import com.meditrack.prescription.application.ports.PrescriptionsPersistenceUseCase;
import com.meditrack.prescription.application.ports.PrescriptionsUseCase;
import com.meditrack.prescription.domain.PrescriptionCreationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrescriptionsUseCaseImpl implements PrescriptionsUseCase {

    private final PrescriptionsPersistenceUseCase prescriptionsPersistenceUseCase;

    @Override
    public Long createPrescription(Long doctorId, PrescriptionCreationRequest prescriptionCreationRequest) {
        Long prescriptionId = prescriptionsPersistenceUseCase.createPrescription(prescriptionCreationRequest.getPatientId(), prescriptionCreationRequest.getAppointmentId(), doctorId);
        return prescriptionId;
    }
}
