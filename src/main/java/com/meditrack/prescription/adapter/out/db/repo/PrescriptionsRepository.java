package com.meditrack.prescription.adapter.out.db.repo;

import com.meditrack.prescription.adapter.out.db.entity.PrescriptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionsRepository extends JpaRepository<PrescriptionsEntity, Long> {

    boolean existsByPatientIdAndAppointmentId(Long patientId, Long appointmentId);
}
