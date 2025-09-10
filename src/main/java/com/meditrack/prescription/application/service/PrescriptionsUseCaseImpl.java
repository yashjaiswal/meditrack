package com.meditrack.prescription.application.service;

import com.meditrack.patient.application.ports.AppointmentPersistenceUseCase;
import com.meditrack.patient.domain.AppointmentRequest;
import com.meditrack.prescription.application.ports.PrescriptionsPersistenceUseCase;
import com.meditrack.prescription.application.ports.PrescriptionsUseCase;
import com.meditrack.prescription.domain.PrescriptionCreationRequest;
import com.meditrack.prescription.domain.UpdatePrescriptionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrescriptionsUseCaseImpl implements PrescriptionsUseCase {

    private final PrescriptionsPersistenceUseCase prescriptionsPersistenceUseCase;
    private final AppointmentPersistenceUseCase appointmentPersistenceUseCase;

    @Override
    public Long createPrescription(Long doctorId, PrescriptionCreationRequest prescriptionCreationRequest) {
        Long prescriptionId = prescriptionsPersistenceUseCase.createPrescription(prescriptionCreationRequest.getPatientId(), prescriptionCreationRequest.getAppointmentId(), doctorId);
        return prescriptionId;
    }

    @Override
    public void updatePrescription(UpdatePrescriptionRequest updatePrescriptionRequest) {
        prescriptionsPersistenceUseCase.updatePrescription(updatePrescriptionRequest);

        // Set an appointment for future date for this patient
        appointmentPersistenceUseCase.storeAppointment(formAppointmentRequest(updatePrescriptionRequest));

    }

    private AppointmentRequest formAppointmentRequest(UpdatePrescriptionRequest updatePrescriptionRequest) {
        AppointmentRequest appointmentRequest = new AppointmentRequest();
        appointmentRequest.setAppointmentDate(updatePrescriptionRequest.getNextAppointmentDate());
        appointmentRequest.setPatientId(updatePrescriptionRequest.getPatientId());
        if (updatePrescriptionRequest.getCommentsForNextAppointment() != null) {
            appointmentRequest.setComments(updatePrescriptionRequest.getCommentsForNextAppointment());
        }

        return appointmentRequest;
    }
}
