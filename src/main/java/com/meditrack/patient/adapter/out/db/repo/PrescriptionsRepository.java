package com.meditrack.patient.adapter.out.db.repo;

import com.meditrack.patient.adapter.out.db.entity.PrescriptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionsRepository extends JpaRepository<PrescriptionsEntity, Long> {
}
