package com.meditrack.patient.adapter.in.web;

import com.meditrack.patient.application.ports.PatientUseCase;
import com.meditrack.patient.domain.AddPatientRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/v1/patient")
@AllArgsConstructor
public class PatientController {

    private final PatientUseCase patientUseCase;

    @PostMapping
    ResponseEntity<Long> addNewPatient(@RequestBody AddPatientRequest addPatientRequest) {
        return ResponseEntity.ok(patientUseCase.createPatient(addPatientRequest));

    }

}
