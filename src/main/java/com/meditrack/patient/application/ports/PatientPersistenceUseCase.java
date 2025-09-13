package com.meditrack.patient.application.ports;

import com.meditrack.patient.domain.AddPatientRequest;
import com.meditrack.patient.domain.Patient;

public interface PatientPersistenceUseCase {

    Long storePatient(AddPatientRequest addPatientRequest);

    Patient getPatientDetails(Long patientId);
}
