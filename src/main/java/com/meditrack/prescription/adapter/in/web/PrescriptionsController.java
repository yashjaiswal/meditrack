package com.meditrack.prescription.adapter.in.web;

import com.meditrack.accounts.domain.errors.InvalidTokenException;
import com.meditrack.prescription.application.ports.PrescriptionsUseCase;
import com.meditrack.prescription.domain.PrescriptionCreationRequest;
import com.meditrack.prescription.domain.PrescriptionCreationResponse;
import com.meditrack.prescription.domain.UpdatePrescriptionRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.meditrack.app.ApplicationConstants.USER_ID;

@RestController
@Slf4j
@RequestMapping("/v1/prescriptions")
@AllArgsConstructor
public class PrescriptionsController {

    private final PrescriptionsUseCase prescriptionsUseCase;

    @PostMapping
    PrescriptionCreationResponse createPrescriptions(@RequestAttribute(USER_ID) Long userId,
                                                     @RequestBody PrescriptionCreationRequest prescriptionCreationRequest) {
        if (Objects.isNull(userId)) {
            throw new InvalidTokenException("UserId not found");
        }

        Long prescriptionId = prescriptionsUseCase.createPrescription(userId, prescriptionCreationRequest);
        PrescriptionCreationResponse prescriptionCreationResponse = new PrescriptionCreationResponse();
        prescriptionCreationResponse.setPrescriptionId(prescriptionId);

        return prescriptionCreationResponse;
    }

    @PatchMapping
    void updatePrescriptions(@RequestAttribute(USER_ID) Long userId,
                             @RequestBody UpdatePrescriptionRequest updatePrescriptionRequest) {
        if (Objects.isNull(userId)) {
            throw new InvalidTokenException("UserId not found");
        }

        prescriptionsUseCase.updatePrescription(updatePrescriptionRequest);

    }
}
