package com.meditrack.patient.application.service;

import com.meditrack.patient.adapter.out.db.entity.PatientsEntity;
import com.meditrack.patient.adapter.out.db.repo.PatientsRepository;
import com.meditrack.patient.application.ports.AppointmentPersistenceUseCase;
import com.meditrack.patient.application.ports.PatientPersistenceUseCase;
import com.meditrack.patient.domain.AddPatientRequest;
import com.meditrack.patient.domain.AppointmentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PatientPersistenceUseCaseImpl implements PatientPersistenceUseCase {

    private final PatientsRepository patientsRepository;
    private final AppointmentPersistenceUseCase appointmentPersistenceUseCase;

    @Override
    public void storePatient(AddPatientRequest addPatientRequest) {
        PatientsEntity patientsEntity = new PatientsEntity();
        patientsEntity.setName(addPatientRequest.getName());
        patientsEntity.setDateOfBirth(addPatientRequest.getDateOfBirth().atStartOfDay());
        patientsEntity.setGender(addPatientRequest.getGender());
        patientsEntity.setComments(addPatientRequest.getComments());
        patientsEntity.setPhoneNumber(addPatientRequest.getPhoneNumber());
        Long patientId = patientsRepository.save(patientsEntity).getId();

        if (Boolean.TRUE.equals(addPatientRequest.getScheduleFirstAppointment())) {
            AppointmentRequest appointmentRequest = new AppointmentRequest();
            appointmentRequest.setPatientId(patientId);
            appointmentRequest.setAppointmentDate(addPatientRequest.getFirstAppointmentDate());

            appointmentPersistenceUseCase.storeAppointment()
        }

    }


}
