package com.meditrack.patient.adapter.out.db.repo;

import com.meditrack.patient.adapter.out.db.entity.AppointmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentsRepository extends JpaRepository<AppointmentsEntity, Long> {

    Boolean existsByIdAndPatientId(Long id, Long patientId);

    AppointmentsEntity findByIdAndPatientId(Long id, Long patientId);
}
