package com.meditrack.patient.adapter.out.db.repo;

import com.meditrack.patient.adapter.out.db.entity.MedicinesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicinesRepository extends JpaRepository<MedicinesEntity, Long> {
}
