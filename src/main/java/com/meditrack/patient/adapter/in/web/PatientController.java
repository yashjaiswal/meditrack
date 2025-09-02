package com.meditrack.patient.adapter.in.web;

import com.meditrack.patient.application.ports.PatientUseCase;
import com.meditrack.patient.domain.AddPatientRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/v1/patient")
public class PatientController {

    private final PatientUseCase patientUseCase;

    @PutMapping
    public void addNewPatient(@RequestBody AddPatientRequest addPatientRequest) {
        patientUseCase.savePatient(addPatientRequest);

    }

}
