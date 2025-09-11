package com.meditrack.prescription.domain;

import lombok.Data;

@Data
public class PrescriptionDosage {

    private Long medicineId;
    private DosageType dosageType;
    private Integer durationInDays;
    private String specialInstruction;

}
