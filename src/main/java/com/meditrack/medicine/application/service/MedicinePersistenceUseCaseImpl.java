package com.meditrack.medicine.application.service;

import com.meditrack.medicine.adapter.out.db.entity.MedicinesEntity;
import com.meditrack.medicine.adapter.out.db.repo.MedicinesRepository;
import com.meditrack.medicine.application.ports.MedicinePersistenceUseCase;
import com.meditrack.medicine.domain.Medicine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public void storeMedicines(List<Medicine> medicineList) {

        for (Medicine medicine : medicineList) {
            MedicinesEntity medicinesEntity = new MedicinesEntity();
            medicinesEntity.setName(medicine.getName());
            medicinesEntity.setStrengthInMg(medicine.getStrengthInMg());
            if (StringUtils.isNotBlank(medicine.getComments())) {
                medicinesEntity.setComments(medicine.getComments());
            }

            medicinesRepository.save(medicinesEntity);
        }
    }

    @Override
    public Medicine getMedicine(Long medicineId) {
        MedicinesEntity medicinesEntity = medicinesRepository.findById(medicineId).get();

        Medicine medicine = new Medicine();
        medicine.setName(medicinesEntity.getName());
        medicine.setStrengthInMg(medicinesEntity.getStrengthInMg());
        medicine.setComments(medicinesEntity.getComments());

        return medicine;

    }

    private Medicine toDto(MedicinesEntity medicinesEntity) {

        Medicine medicine = new Medicine();
        medicine.setName(medicinesEntity.getName());
        medicine.setStrengthInMg(medicinesEntity.getStrengthInMg());
        medicine.setComments(medicinesEntity.getComments());
        medicine.setMedicineId(medicinesEntity.getId());

        return medicine;
    }
}
