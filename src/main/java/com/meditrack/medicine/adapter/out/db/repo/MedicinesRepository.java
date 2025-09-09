package com.meditrack.medicine.adapter.out.db.repo;

import com.meditrack.medicine.adapter.out.db.entity.MedicinesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicinesRepository extends JpaRepository<MedicinesEntity, Long> {

}
