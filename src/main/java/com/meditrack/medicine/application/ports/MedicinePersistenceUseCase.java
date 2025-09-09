package com.meditrack.medicine.application.ports;

import com.meditrack.medicine.domain.Medicine;

import java.util.List;

public interface MedicinePersistenceUseCase {

    List<Medicine> getAllMedicines();
}
