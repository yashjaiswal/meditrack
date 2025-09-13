package com.meditrack.medicine.adapter.in.web;

import com.meditrack.medicine.application.ports.MedicinePersistenceUseCase;
import com.meditrack.medicine.domain.Medicine;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/v1/medicine")
@AllArgsConstructor
public class MedicineController {

    private final MedicinePersistenceUseCase medicinePersistenceUseCase;

    @GetMapping
    List<Medicine> getAllMedicines() {

        return medicinePersistenceUseCase.getAllMedicines();
    }

    @PostMapping
    void storeMedicine(@RequestBody List<Medicine> medicines) {

        medicinePersistenceUseCase.storeMedicines(medicines);
    }
}
