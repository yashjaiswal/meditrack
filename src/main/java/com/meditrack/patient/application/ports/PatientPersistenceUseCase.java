package com.meditrack.patient.application.ports;

import com.meditrack.patient.domain.AddPatientRequest;

public interface PatientPersistenceUseCase {

    Long storePatient(AddPatientRequest addPatientRequest);
}
