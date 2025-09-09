package com.meditrack.medicine.application.service;

import com.meditrack.medicine.adapter.out.db.entity.MedicinesEntity;
import com.meditrack.medicine.adapter.out.db.repo.MedicinesRepository;
import com.meditrack.medicine.application.ports.MedicinePersistenceUseCase;
import com.meditrack.medicine.domain.Medicine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MedicinePersistenceUseCaseImpl implements MedicinePersistenceUseCase {

    private final MedicinesRepository medicinesRepository;

    @Override
    public List<Medicine> getAllMedicines() {
        List<MedicinesEntity> medicinesEntities = medicinesRepository.findAll();
        List<Medicine> medicines = medicinesEntities.stream()
                .map(this::toDto)
                .toList();

        return medicines;
    }

    private Medicine toDto(MedicinesEntity medicinesEntity) {

        Medicine medicine = new Medicine();
        medicine.setName(medicinesEntity.getName());
        medicine.setStrengthInMg(medicinesEntity.getStrengthInMg());

        return medicine;
    }
}
