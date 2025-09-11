package com.meditrack.prescription.domain;

import lombok.Data;

@Data
public class Dosage {

    private Long medicineId;
    private DosageType dosageType;
    private Integer durationInDays;
    private String specialInstruction;

}
