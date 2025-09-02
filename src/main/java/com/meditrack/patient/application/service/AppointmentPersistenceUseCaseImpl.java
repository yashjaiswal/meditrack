package com.meditrack.patient.application.service;

import com.meditrack.patient.adapter.out.db.entity.AppointmentsEntity;
import com.meditrack.patient.adapter.out.db.repo.AppointmentsRepository;
import com.meditrack.patient.application.ports.AppointmentPersistenceUseCase;
import com.meditrack.patient.domain.AppointmentRequest;
import com.meditrack.patient.domain.PatientAndAppointment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppointmentPersistenceUseCaseImpl implements AppointmentPersistenceUseCase {

    private final AppointmentsRepository appointmentsRepository;

    @Override
    public PatientAndAppointment storeAppointment(AppointmentRequest appointmentRequest) {
        AppointmentsEntity appointmentsEntity = new AppointmentsEntity();
        appointmentsEntity.setPatientId(appointmentRequest.getPatientId());
        appointmentsEntity.setDateOfAppointment(appointmentRequest.getAppointmentDate().atStartOfDay());

        if (!appointmentRequest.getComments().isBlank()) {
            appointmentsEntity.setComments(appointmentRequest.getComments());
        }

        if(Objects.nonNull(appointmentRequest.getIsPresent())) {
            appointmentsEntity.setIsPresent(appointmentRequest.getIsPresent());
        }

        Long appointmentId = appointmentsRepository.save(appointmentsEntity).getId();

        PatientAndAppointment patientAndAppointment = new PatientAndAppointment();
        patientAndAppointment.setAppointmentId(appointmentId);
        patientAndAppointment.setPatientId(appointmentRequest.getPatientId());

        return patientAndAppointment;
    }

    @Override
    public void markAsPresent(Long patientId, Long appointmentId) {
        if (appointmentsRepository.existsByPatientIdAndAppointmentId(patientId, appointmentId)) {
            AppointmentsEntity appointmentsEntity = appointmentsRepository.findByPatientIdAndAppointmentId(patientId, appointmentId);
            appointmentsEntity.setIsPresent(true);
            appointmentsRepository.save(appointmentsEntity);
        }
    }
}
