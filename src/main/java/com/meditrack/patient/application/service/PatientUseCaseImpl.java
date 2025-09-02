package com.meditrack.patient.application.service;

import com.meditrack.patient.application.ports.PatientPersistenceUseCase;
import com.meditrack.patient.application.ports.PatientUseCase;
import com.meditrack.patient.domain.AddPatientRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PatientUseCaseImpl implements PatientUseCase {

    private final PatientPersistenceUseCase patientPersistenceUseCase;

    public void createPatient(AddPatientRequest addPatientRequest) {
        patientPersistenceUseCase.storePatient(addPatientRequest);

    }
}
