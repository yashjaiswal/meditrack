package com.meditrack.prescription.application.service;

import com.meditrack.prescription.adapter.out.db.entity.PrescriptionsEntity;
import com.meditrack.prescription.adapter.out.db.repo.PrescriptionsRepository;
import com.meditrack.prescription.application.ports.PrescriptionsPersistenceUseCase;
import com.meditrack.prescription.domain.Dosage;
import com.meditrack.prescription.domain.DosageType;
import com.meditrack.prescription.domain.PrescriptionDetails;
import com.meditrack.prescription.domain.UpdatePrescriptionRequest;
import com.meditrack.prescription.domain.errors.PrescriptionAlreadyExistsException;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrescriptionsPersistenceUseCaseImpl implements PrescriptionsPersistenceUseCase {

    private final PrescriptionsRepository prescriptionsRepository;

    @Override
    public Long createPrescription(Long patientId, Long appointmentId, Long doctorId) {
        if (prescriptionsRepository.existsByPatientIdAndAppointmentId(patientId, appointmentId)) {
            throw new PrescriptionAlreadyExistsException("A prescription already exists for this patient and appointment");
        }

        PrescriptionsEntity prescriptionsEntity = new PrescriptionsEntity();
        prescriptionsEntity.setAppointmentId(appointmentId);
        prescriptionsEntity.setDoctorId(doctorId);
        prescriptionsEntity.setPatientId(patientId);
        Long prescriptionId = prescriptionsRepository.save(prescriptionsEntity).getId();

        return prescriptionId;
    }

    @Override
    public void updatePrescription(UpdatePrescriptionRequest updatePrescriptionRequest) {
        PrescriptionsEntity prescriptionsEntity = prescriptionsRepository.findById(updatePrescriptionRequest.getPrescriptionId()).get();

        List<Dosage> dosages = updatePrescriptionRequest.getDosages();
        PrescriptionDetails prescriptionDetails = new PrescriptionDetails();
        prescriptionDetails.setDosageList(dosages);

        prescriptionsEntity.setPrescriptionDetails(prescriptionDetails);
        prescriptionsRepository.save(prescriptionsEntity);

    }
}
