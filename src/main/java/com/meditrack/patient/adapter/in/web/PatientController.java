package com.meditrack.patient.adapter.in.web;

import com.meditrack.patient.application.ports.PatientUseCase;
import com.meditrack.patient.domain.AddPatientRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/v1/patient")
@AllArgsConstructor
public class PatientController {

    private final PatientUseCase patientUseCase;

    @PutMapping
    ResponseEntity<Long> addNewPatient(@RequestBody AddPatientRequest addPatientRequest) {
        return ResponseEntity.ok(patientUseCase.createPatient(addPatientRequest));

    }

}
