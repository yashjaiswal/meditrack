package com.meditrack.patient.adapter.in.web;

import com.meditrack.patient.application.ports.AppointmentPersistenceUseCase;
import com.meditrack.patient.domain.AppointmentRequest;
import com.meditrack.patient.domain.PatientAndAppointment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/v1/appointment")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentPersistenceUseCase appointmentPersistenceUseCase;

    @PutMapping
    PatientAndAppointment scheduleAppointment(AppointmentRequest appointmentRequest) {
        PatientAndAppointment patientAndAppointment =
                appointmentPersistenceUseCase.storeAppointment(appointmentRequest);
        return patientAndAppointment;
    }

    @PatchMapping
    void markPresentForAppointment(Long appointmentId, Long patientId) {
        appointmentPersistenceUseCase.markAsPresent(patientId, appointmentId);
    }
}
