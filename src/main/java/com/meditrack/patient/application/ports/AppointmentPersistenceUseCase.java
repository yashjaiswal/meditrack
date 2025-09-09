package com.meditrack.patient.application.ports;

import com.meditrack.patient.domain.AppointmentRequest;
import com.meditrack.patient.domain.PatientAndAppointment;

public interface AppointmentPersistenceUseCase {

    PatientAndAppointment storeAppointment(AppointmentRequest appointmentRequest);

    void markAsPresent(Long patientId, Long appointmentId);
}
