package com.meditrack.patient.application.ports;

import com.meditrack.patient.domain.AddPatientRequest;

public interface PatientUseCase {

    void createPatient(AddPatientRequest addPatientRequest);
}
